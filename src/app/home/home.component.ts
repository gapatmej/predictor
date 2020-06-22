import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { HttpResponse } from '@angular/common/http';

import { RestrictionService } from './../services/restriction.service'
import { IRestriction } from '../shared/model/restriction.model';
import { AlertService } from '../alert';

import * as HttpStatus from 'http-status-codes'

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  formHome: FormGroup;
  submitted = false;
  restrictions: IRestriction[];

  get f() { return this.formHome.controls; }

  options = {
    autoClose: true,
    keepAfterRouteChange: false
  };

  constructor(private formBuilder: FormBuilder, private restrictionService: RestrictionService,
    protected alertService: AlertService) {
    this.formHome = this.formBuilder.group({
      licensePlateNumber: ['', Validators.required],
      date: ['', Validators.required],
      hours: ['', Validators.required],
      minutes: ['', Validators.required],
    });
  }

  ngOnInit(): void {
  }

  pruebas(){
    this.alertService.success('aaaa!!');
  }

  onSubmit() {
    this.submitted = true;

    if (this.formHome.invalid) {
      return;
    }

    let params = { ...this.formHome.value };
    params.date = params.date.replace(/\//g, '');
    this.restrictionService
      .find(params)
      .subscribe(
        (res: HttpResponse<IRestriction[]>) => this.onNotPermited(res.body),
        (res: HttpResponse<any>) => this.onError(res)
      );
  }

  onReset() {
    this.submitted = false;
    this.formHome.reset();
  }

  onGetCurrentDate() {
    let date = new Date();
    this.formHome.controls.date.setValue(date.toLocaleDateString('en-GB'));
    this.formHome.controls.hours.setValue(date.getHours());
    this.formHome.controls.minutes.setValue(date.getMinutes());
  }

  private onNotPermited(data) {
    this.restrictions = data;
    this.alertService.warn("¡ Tienes restricciones, no puedes circular !");
  }

  private onError(error) {
    this.restrictions = null;
    if(error.status === HttpStatus.NOT_FOUND ){
      this.alertService.success("¡ No tienes restricciones, puedes circular !");
      return;
    }

    this.alertService.error("Error!! Intentalo mas tarde");
  }

}
