import { Component, OnInit } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { HttpResponse } from '@angular/common/http';

import { RestrictionService } from './../services/restriction.service'
import { IRestriction } from '../shared/model/restriction.model';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  formHome;
  restrictions: IRestriction[];

  constructor(private formBuilder: FormBuilder, private restrictionService: RestrictionService) {
    this.formHome = this.formBuilder.group({
      licensePlateNumber: '',
      date: '',
      hours: Number,
      minutes: Number
    });
  }

  ngOnInit(): void {
  }

  onSubmit(formHome) {
    this.restrictionService
      .find(formHome)
      .subscribe(
        (res: HttpResponse<IRestriction[]>) => this.onSuccess(res.body),
        (res: HttpResponse<any>) => this.onError(res.body)
      );
  }

  private onSuccess(data) {
    this.restrictions = data;
  }

  private onError(error) {
    alert(error);
  }

}
