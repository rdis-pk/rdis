import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IPerson } from 'app/shared/model/person.model';

type EntityResponseType = HttpResponse<IPerson>;
type EntityArrayResponseType = HttpResponse<IPerson[]>;

@Injectable({ providedIn: 'root' })
export class PersonService {
  public resourceUrl = SERVER_API_URL + 'api/people';

  constructor(protected http: HttpClient) {}
  // tslint:disable-next-line: typedef
  private static _handleError(err: HttpErrorResponse | any) {
    return Observable.throw((err.messgae || '') + 'Error: Unable to complete request.');
  }

  create(person: IPerson): Observable<EntityResponseType> {
    return this.http
      .post<IPerson>(this.resourceUrl, person, { observe: 'response' })
      .pipe(PersonService._handleError);
  }

  update(person: IPerson): Observable<EntityResponseType> {
    return this.http.put<IPerson>(this.resourceUrl, person, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IPerson>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  searchByCNIC(cnic: String): Observable<EntityResponseType> {
    return this.http.get<IPerson>(`${this.resourceUrl}/cnic/${cnic}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IPerson[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
