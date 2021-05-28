import { User } from "./user.model";
import { BiopsyAnalysis } from "./biopsy-analysis.model";
import { UltrasoundAnalysis } from "./ultrasound-analysis.model";
import { PatientInfo } from "./patient-info.model";

export interface Analysis {
  id: number
  createdBy: User,
  updatedBy: User,
  patientInfo: PatientInfo,
  biopsyAnalysis: BiopsyAnalysis,
  ultrasoundAnalysis: UltrasoundAnalysis,
  created: Date,
  updated: Date
}
