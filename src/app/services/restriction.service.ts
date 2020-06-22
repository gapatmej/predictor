import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

type EntityResponseType = HttpResponse<IRestriction[]>;

import {environment} from 'src/environments/environment';
import { IRestriction } from '../shared/model/restriction.model';

@Injectable({
  providedIn: 'root'
})
export class RestrictionService {

  constructor(protected http: HttpClient) { }

  find(params: any  ): Observable<EntityResponseType> {
    return this.http
        .get<IRestriction[]>(`${environment.SERVER_API_URL}/restriction/${params.licensePlateNumber}/${params.date}/${params.hours}/${params.minutes}`, { observe: 'response' })
}
}
