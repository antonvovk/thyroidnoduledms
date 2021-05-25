import { QualificationQuestion } from "./qualification-question.mode";
import { QualificationGivenAnswer } from "./qualification-given-answer.model";

export interface QualificationAnsweredQuestion {
  question: QualificationQuestion
  givenAnswer: QualificationGivenAnswer
}
