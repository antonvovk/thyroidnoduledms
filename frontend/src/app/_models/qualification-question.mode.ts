import { QualificationAnswer } from "./qualification-answer.model";

export interface QualificationQuestion {
  id: number,
  text: string
  answer: QualificationAnswer
  imageUrl?: string
}
