import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators, FormsModule, ReactiveFormsModule } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IPerson, Person } from '../../../app/shared/model/person.model';
import { PersonService } from './person.service';
import { EROFS } from 'constants';

@Component({
  selector: 'jhi-person-search',
  templateUrl: './person-search.component.html'
})
export class PersonSearchComponent implements OnInit {
  isSaving = false;
  person: IPerson | null = null;

  editForm = this.fb.group({
    cnic: [null, [Validators.required, Validators.maxLength(13)]]
  });

  constructor(protected personService: PersonService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ person }) => {
      this.updateForm(person);
    });
  }

  updateForm(person: IPerson): void {
    this.editForm.patchValue({
      cnic: person.cnic
    });
  }

  previousState(): void {
    window.history.back();
  }

  search(): void {
    this.isSaving = true;
    const person = this.createFromForm();
    const cnic = person.cnic as String;

    this.subscribeToSearchResponse(this.personService.searchByCNIC(cnic));
  }

  private createFromForm(): IPerson {
    return {
      ...new Person(),
      cnic: this.editForm.get(['cnic'])!.value
    };
  }

  protected subscribeToSearchResponse(result: Observable<HttpResponse<IPerson>>): void {
    result.subscribe(
      () => this.onSearchSuccess(result),
      () => this.onSaveError()
    );
  }

  protected onSearchSuccess(result: Observable<HttpResponse<IPerson>>): void {
    this.isSaving = false;

    result.subscribe(resp => {
      this.person = resp.body;
    });
  }

  protected onSaveError(): void {
    this.isSaving = false;
    this.person = null;
  }
}
