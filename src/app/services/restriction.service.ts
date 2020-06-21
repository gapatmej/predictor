import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

type EntityResponseType = HttpResponse<IRestriction[]>;

import {environment} from 'src/environments/environment';
import { map } from 'rxjs/operators';
import { IRestriction } from '../shared/model/restriction.model';
import { Day } from '../shared/model/restriction.model';

@Injectable({
  providedIn: 'root'
})
export class RestrictionService {

  constructor(protected http: HttpClient) { }

  find(req: any  ): Observable<EntityResponseType> {
    console.log(req);
    return this.http
        .get<IRestriction[]>(`${environment.SERVER_API_URL}/restriction/${req.licensePlateNumber}/${req.day}/${req.hours}/${req.minutes}`, { observe: 'response' })
}
}
