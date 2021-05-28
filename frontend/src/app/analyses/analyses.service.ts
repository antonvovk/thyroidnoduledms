import { Injectable } from "@angular/core";
import { environment } from "../../environments/environment";
import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs";
import { Analysis } from "../_models/analysis.model";
import { UltrasoundImage } from "../_models/ultrasound-image.model";

@Injectable()
export class AnalysesService {

  private readonly API_URL = `${environment.apiUrl}/analysis`

  constructor(private http: HttpClient) {
  }

  getAll(): Observable<Analysis[]> {
    return this.http.get<Analysis[]>(this.API_URL);
  }

  create(analysis: Analysis): Observable<Analysis> {
    return this.http.post<Analysis>(this.API_URL, analysis)
  }

  update(analysis: Analysis): Observable<Analysis> {
    return this.http.put<Analysis>(this.API_URL, analysis)
  }

  postFile(fileToUpload: File): Observable<any> {
    const formData: FormData = new FormData();
    formData.append('file', fileToUpload, fileToUpload.name);
    return this.http.post(`${environment.apiUrl}/files`, formData)
  }

  addImage(id: number, image: UltrasoundImage): Observable<any> {
    return this.http.post(`${this.API_URL}/${id}/image`, image)
  }

  removeImage(id: number, image: UltrasoundImage): Observable<any> {
    return this.http.put(`${this.API_URL}/${id}/image`, image)
  }
}
