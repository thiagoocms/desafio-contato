import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ListContato, SimpleContato } from '../model/contato.model';
import { API_CONFIG } from '../utils';

@Injectable({
  providedIn: 'root'
})
export class ContatoService {

  constructor(private http: HttpClient) { }

   private localUrl = API_CONFIG.BASE_URL + "/v1/contatos"

  findAll(): Observable<ListContato> {
    return this.http.get<ListContato>(this.localUrl)
  }

  create(contato: SimpleContato): Observable<SimpleContato> {
    return this.http.post<SimpleContato>(this.localUrl, contato)
  }

  findById(id: number): Observable<SimpleContato> {
    return this.http.get<SimpleContato>(`${this.localUrl}/${id}`)
  }

  update(id: number, contato: SimpleContato): Observable<SimpleContato> {
    return this.http.patch<SimpleContato>(`${this.localUrl}/${id}`, contato)
  }

  favorite(id: number): Observable<void> {
    return this.http.patch<void>(`${this.localUrl}/${id}/favoritar`, {})
  }

  unFavorite(id: number): Observable<void> {
    return this.http.patch<void>(`${this.localUrl}/${id}/desfavoritar`, {})
  }

  deactivate(id: number): Observable<void> {
    return this.http.patch<void>(`${this.localUrl}/${id}/desativar`, {})
  }
}
