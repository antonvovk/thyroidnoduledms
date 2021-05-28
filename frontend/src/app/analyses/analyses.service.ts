import { Injectable } from "@angular/core";
import { environment } from "../../environments/environment";
import { HttpClient, HttpParams } from "@angular/common/http";
import { Observable } from "rxjs";
import { Analysis } from "../_models/analysis.model";
import { UltrasoundImage } from "../_models/ultrasound-image.model";
import { Page } from "../_models/page.model";

@Injectable()
export class AnalysesService {

  private readonly API_URL = `${environment.apiUrl}/analysis`

  constructor(private http: HttpClient) {
  }

  getAll(page: number, size: number): Observable<Page<Analysis>> {
    let params = new HttpParams()
    params = params.append('page', page)
    params = params.append('size', size)

    return this.http.get<Page<Analysis>>(this.API_URL, {params: params});
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

  loadExel(exel: File): Observable<any> {
    const formData: FormData = new FormData();
    formData.append('file', exel, exel.name);
    return this.http.post(`${this.API_URL}/exel`, formData)
  }

  addImage(id: number, image: UltrasoundImage): Observable<any> {
    return this.http.post(`${this.API_URL}/${id}/image`, image)
  }

  removeImage(id: number, image: UltrasoundImage): Observable<any> {
    return this.http.put(`${this.API_URL}/${id}/image`, image)
  }
}
