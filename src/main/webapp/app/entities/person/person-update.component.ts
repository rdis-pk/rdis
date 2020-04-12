import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IPerson, Person } from 'app/shared/model/person.model';
import { PersonService } from './person.service';

@Component({
  selector: 'jhi-person-update',
  templateUrl: './person-update.component.html'
})
export class PersonUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    fullName: [null, [Validators.required, Validators.maxLength(500)]],
    fatherName: [],
    husbandName: [],
    email: [],
    mobile: [null, [Validators.required, Validators.maxLength(12)]],
    otherContactDetails: [null, [Validators.maxLength(100)]],
    cnic: [null, [Validators.required, Validators.maxLength(13)]],
    homeAddress: [],
    area: [],
    city: [null, [Validators.required, Validators.maxLength(50)]],
    postcode: [null, [Validators.required, Validators.maxLength(10)]],
    country: [null, [Validators.required, Validators.maxLength(2)]],
    status: []
  });

  constructor(protected personService: PersonService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ person }) => {
      this.updateForm(person);
    });
  }

  updateForm(person: IPerson): void {
    this.editForm.patchValue({
      id: person.id,
      fullName: person.fullName,
      fatherName: person.fatherName,
      husbandName: person.husbandName,
      email: person.email,
      mobile: person.mobile,
      otherContactDetails: person.otherContactDetails,
      cnic: person.cnic,
      homeAddress: person.homeAddress,
      area: person.area,
      city: person.city,
      postcode: person.postcode,
      country: person.country,
      status: person.status
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const person = this.createFromForm();
    if (person.id !== undefined) {
      this.subscribeToSaveResponse(this.personService.update(person));
    } else {
      this.subscribeToSaveResponse(this.personService.create(person));
    }
  }

  private createFromForm(): IPerson {
    return {
      ...new Person(),
      id: this.editForm.get(['id'])!.value,
      fullName: this.editForm.get(['fullName'])!.value,
      fatherName: this.editForm.get(['fatherName'])!.value,
      husbandName: this.editForm.get(['husbandName'])!.value,
      email: this.editForm.get(['email'])!.value,
      mobile: this.editForm.get(['mobile'])!.value,
      otherContactDetails: this.editForm.get(['otherContactDetails'])!.value,
      cnic: this.editForm.get(['cnic'])!.value,
      homeAddress: this.editForm.get(['homeAddress'])!.value,
      area: this.editForm.get(['area'])!.value,
      city: this.editForm.get(['city'])!.value,
      postcode: this.editForm.get(['postcode'])!.value,
      country: this.editForm.get(['country'])!.value,
      status: this.editForm.get(['status'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IPerson>>): void {
    result.subscribe(
      () => this.onSaveSuccess(),
      () => this.onSaveError()
    );
  }

  protected onSaveSuccess(): void {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError(): void {
    this.isSaving = false;
  }
}
