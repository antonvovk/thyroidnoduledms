import { Injectable } from "@angular/core";
import { environment } from "../../environments/environment";
import { HttpClient } from "@angular/common/http";
import { QualificationQuestion } from "../_models/qualification-question.mode";
import { Observable } from "rxjs";
import { QualificationAnsweredQuestion } from "../_models/qualification-answered-question.mode";

@Injectable()
export class QualificationService {

  private readonly API_URL = `${environment.apiUrl}/qualification`

  constructor(private http: HttpClient) {
  }

  getAllQuestions(): Observable<QualificationQuestion[]> {
    return this.http.get<QualificationQuestion[]>(this.API_URL)
  }

  testQualification(answeredQuestion: QualificationAnsweredQuestion[]): Observable<any> {
    return this.http.post(this.API_URL, answeredQuestion)
  }
}
