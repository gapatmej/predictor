import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { HttpResponse } from '@angular/common/http';

import { RestrictionService } from './../services/restriction.service'
import { IRestriction } from '../shared/model/restriction.model';

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

  constructor(private formBuilder: FormBuilder, private restrictionService: RestrictionService) {
    this.formHome = this.formBuilder.group({
      licensePlateNumber: ['', Validators.required],
      date: ['', Validators.required],
      hours: ['', Validators.required],
      minutes: ['', Validators.required],
    });
  }

  ngOnInit(): void {
  }

  onSubmit() {
    this.submitted = true;
    console.log(this.formHome);

    // stop here if form is invalid
    if (this.formHome.invalid) {
      return;
    }

    this.restrictionService
      .find(this.formHome.value)
      .subscribe(
        (res: HttpResponse<IRestriction[]>) => this.onSuccess(res.body),
        (res: HttpResponse<any>) => this.onError(res.body)
      );
  }

  onReset() {
    this.submitted = false;
    this.formHome.reset();
  }
  
  onGetCurrentDate(){
    let date = new Date();
    this.formHome.controls.date.setValue(date.toLocaleDateString('en-GB'));
    this.formHome.controls.hours.setValue(date.getHours());
    this.formHome.controls.minutes.setValue(date.getMinutes());
  }

  private onSuccess(data) {
    this.restrictions = data;
  }

  private onError(error) {
    alert(error);
  }

}
