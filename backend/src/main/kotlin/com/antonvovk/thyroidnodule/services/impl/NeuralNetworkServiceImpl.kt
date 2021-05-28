package com.antonvovk.thyroidnodule.services.impl

import com.antonvovk.thyroidnodule.db.analyses.model.*
import com.antonvovk.thyroidnodule.db.analyses.repositories.AnalysisRepository
import com.antonvovk.thyroidnodule.services.NeuralNetworkService
import org.datavec.api.records.reader.impl.collection.ListStringRecordReader
import org.datavec.api.split.ListStringSplit
import org.deeplearning4j.datasets.datavec.RecordReaderDataSetIterator
import org.deeplearning4j.nn.conf.BackpropType
import org.deeplearning4j.nn.conf.NeuralNetConfiguration
import org.deeplearning4j.nn.conf.layers.DenseLayer
import org.deeplearning4j.nn.conf.layers.OutputLayer
import org.deeplearning4j.nn.multilayer.MultiLayerNetwork
import org.deeplearning4j.nn.weights.WeightInit
import org.nd4j.evaluation.classification.Evaluation
import org.nd4j.linalg.activations.Activation
import org.nd4j.linalg.api.ndarray.INDArray
import org.nd4j.linalg.dataset.DataSet
import org.nd4j.linalg.dataset.api.iterator.DataSetIterator
import org.nd4j.linalg.dataset.api.preprocessor.DataNormalization
import org.nd4j.linalg.dataset.api.preprocessor.NormalizerStandardize
import org.nd4j.linalg.lossfunctions.LossFunctions
import org.springframework.stereotype.Service

@Service
class NeuralNetworkServiceImpl(
    private val analysisRepository: AnalysisRepository
) : NeuralNetworkService {

    private lateinit var network: MultiLayerNetwork

    override fun predict(analysis: Analysis): String {
        val reader = ListStringRecordReader()
        reader.initialize(ListStringSplit(listOf(convertAnalysis(analysis))))
        val iterator: DataSetIterator = RecordReaderDataSetIterator(
            reader, 1, 34, 2
        )
        val allData: DataSet = iterator.next()
        allData.labelNames = listOf("0", "1")

        return network.predict(allData).single()
    }

    override fun init() {
        val analyses = analysisRepository.findAll()
        val list = mutableListOf<List<String>>()

        for (analysis in analyses) {
            list.add(convertAnalysis(analysis))
        }

        val reader = ListStringRecordReader()
        reader.initialize(ListStringSplit(list))

        val iterator: DataSetIterator = RecordReaderDataSetIterator(
            reader, list.size, 34, 2
        )
        val allData: DataSet = iterator.next()

        val normalizer: DataNormalization = NormalizerStandardize()
        normalizer.fit(allData)
        normalizer.transform(allData)
        allData.labelNames = listOf("0", "1")

        val testAndTrain = allData.splitTestAndTrain(0.65)
        val trainingData = testAndTrain.train
        val testData = testAndTrain.test

        val rngSeed = 123L // integer for reproducability of a random number generator

        val conf = NeuralNetConfiguration.Builder()
            .seed(rngSeed)
            .activation(Activation.RELU)
            .weightInit(WeightInit.XAVIER)
            .l2(0.0001)
            .list()
            .layer(
                DenseLayer.Builder()
                    .nIn(34) // Number of input datapoints.
                    .nOut(2) // Number of output datapoints.
                    .build()
            )
            .layer(
                OutputLayer.Builder(LossFunctions.LossFunction.NEGATIVELOGLIKELIHOOD)
                    .nIn(2)
                    .nOut(2)
                    .activation(Activation.SOFTMAX)
                    .build()
            )
            .backpropType(BackpropType.Standard)
            .build()

        // create the MLN
        network = MultiLayerNetwork(conf)
        network.init()
        network.fit(trainingData)

        val output: INDArray = network.output(testData.features)
        val eval = Evaluation(2)
        eval.eval(testData.labels, output)
        println(eval.stats())
    }

    private fun convertAnalysis(analysis: Analysis): List<String> {
        val list = mutableListOf<String>()

        if (analysis.patientInfo.sex == Sex.M) {
            list.add("1")
        } else {
            list.add("0")
        }

        if (analysis.patientInfo.sex == Sex.F) {
            list.add("1")
        } else {
            list.add("0")
        }

        list.add(analysis.patientInfo.age.toString())

        if (analysis.ultrasoundAnalysis.size == NoduleSize.LESS_THAN_ONE) {
            list.add("1")
        } else {
            list.add("0")
        }

        if (analysis.ultrasoundAnalysis.size == NoduleSize.BETWEEN_ONE_AND_TWO) {
            list.add("1")
        } else {
            list.add("0")
        }

        if (analysis.ultrasoundAnalysis.size == NoduleSize.MORE_THAN_TWO) {
            list.add("1")
        } else {
            list.add("0")
        }

        if (analysis.ultrasoundAnalysis.hasConglomerate) {
            list.add("1")
        } else {
            list.add("0")
        }

        if (analysis.ultrasoundAnalysis.shape == NoduleShape.OVAL) {
            list.add("1")
        } else {
            list.add("0")
        }

        if (analysis.ultrasoundAnalysis.shape == NoduleShape.ROUND) {
            list.add("1")
        } else {
            list.add("0")
        }

        if (analysis.ultrasoundAnalysis.shape == NoduleShape.IRREGULAR) {
            list.add("1")
        } else {
            list.add("0")
        }

        if (analysis.ultrasoundAnalysis.contours == NoduleContours.CLEAR_EVEN) {
            list.add("1")
        } else {
            list.add("0")
        }

        if (analysis.ultrasoundAnalysis.contours == NoduleContours.CLEAR_UNEVEN) {
            list.add("1")
        } else {
            list.add("0")
        }

        if (analysis.ultrasoundAnalysis.contours == NoduleContours.FUZZY_EVEN) {
            list.add("1")
        } else {
            list.add("0")
        }

        if (analysis.ultrasoundAnalysis.contours == NoduleContours.FUZZY_UNEVEN) {
            list.add("1")
        } else {
            list.add("0")
        }

        if (analysis.ultrasoundAnalysis.structure.contains(NoduleStructure.HOMOGENEOUS)) {
            list.add("1")
        } else {
            list.add("0")
        }

        if (analysis.ultrasoundAnalysis.structure.contains(NoduleStructure.HETEROGENEOUS)) {
            list.add("1")
        } else {
            list.add("0")
        }

        if (analysis.ultrasoundAnalysis.structure.contains(NoduleStructure.SPONGY)) {
            list.add("1")
        } else {
            list.add("0")
        }

        if (analysis.ultrasoundAnalysis.structure.contains(NoduleStructure.INHOMOGENEOUS_DUE_TO_CENTRAL_CYSTS)) {
            list.add("1")
        } else {
            list.add("0")
        }

        if (analysis.ultrasoundAnalysis.structure.contains(NoduleStructure.INHOMOGENEOUS_DUE_TO_PERIPHERAL_CYSTS)) {
            list.add("1")
        } else {
            list.add("0")
        }

        if (analysis.ultrasoundAnalysis.structure.contains(NoduleStructure.MACROCALCINATES)) {
            list.add("1")
        } else {
            list.add("0")
        }

        if (analysis.ultrasoundAnalysis.structure.contains(NoduleStructure.MICROCALCINATES)) {
            list.add("1")
        } else {
            list.add("0")
        }

        if (analysis.ultrasoundAnalysis.echogenicity == NoduleEchogenicity.HYPOECHOIC) {
            list.add("1")
        } else {
            list.add("0")
        }

        if (analysis.ultrasoundAnalysis.echogenicity == NoduleEchogenicity.ISOECHOIC) {
            list.add("1")
        } else {
            list.add("0")
        }

        if (analysis.ultrasoundAnalysis.vascularization == NoduleVascularization.CENTRAL) {
            list.add("1")
        } else {
            list.add("0")
        }

        if (analysis.ultrasoundAnalysis.vascularization == NoduleVascularization.PERIPHERAL) {
            list.add("1")
        } else {
            list.add("0")
        }

        if (analysis.ultrasoundAnalysis.vascularization == NoduleVascularization.MIXED) {
            list.add("1")
        } else {
            list.add("0")
        }

        if (analysis.ultrasoundAnalysis.elastography == NoduleElastography.TYPE2) {
            list.add("1")
        } else {
            list.add("0")
        }

        if (analysis.ultrasoundAnalysis.elastography == NoduleElastography.TYPE3) {
            list.add("1")
        } else {
            list.add("0")
        }

        if (analysis.ultrasoundAnalysis.elastography == NoduleElastography.TYPE4) {
            list.add("1")
        } else {
            list.add("0")
        }

        if (analysis.ultrasoundAnalysis.autoimmuneThyroiditis) {
            list.add("1")
        } else {
            list.add("0")
        }


        if (analysis.ultrasoundAnalysis.suspiciousLymphNodes) {
            list.add("1")
        } else {
            list.add("0")
        }

        if (analysis.ultrasoundAnalysis.thirads == Thirads.CLASS2) {
            list.add("1")
        } else {
            list.add("0")
        }

        if (analysis.ultrasoundAnalysis.thirads == Thirads.CLASS3) {
            list.add("1")
        } else {
            list.add("0")
        }

        if (analysis.ultrasoundAnalysis.thirads == Thirads.CLASS4) {
            list.add("1")
        } else {
            list.add("0")
        }

        if (analysis.biopsyAnalysis.bethesdaLevel == BethesdaLevel.CLASS6) {
            list.add("1")
        } else {
            list.add("0")
        }

        return list
    }
}
