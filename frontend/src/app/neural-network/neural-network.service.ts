import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import { Analysis } from "../_models/analysis.model";
import { Observable } from "rxjs";
import { environment } from "../../environments/environment";

@Injectable()
export class NeuralNetworkService {

  constructor(private http: HttpClient) {
  }

  predict(analysis: Analysis): Observable<string> {
    return this.http.post<string>(`${environment.apiUrl}/ai`, analysis)
  }
}
