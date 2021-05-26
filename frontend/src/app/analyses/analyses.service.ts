import { Injectable } from "@angular/core";
import { environment } from "../../environments/environment";
import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs";
import { Analysis } from "../_models/analysis.model";

@Injectable()
export class AnalysesService {

  private readonly API_URL = `${environment.apiUrl}/analysis`

  constructor(private http: HttpClient) {
  }

  getAll(): Observable<Analysis[]> {
    return this.http.get<Analysis[]>(this.API_URL);
  }
}
