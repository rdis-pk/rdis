import { SupportStatus } from 'app/shared/model/enumerations/support-status.model';

export interface IPerson {
  id?: number;
  fullName?: string;
  fatherName?: string;
  husbandName?: string;
  email?: string;
  mobile?: string;
  otherContactDetails?: string;
  cnic?: string;
  homeAddress?: string;
  area?: string;
  city?: string;
  postcode?: string;
  country?: string;
  status?: SupportStatus;
}

export class Person implements IPerson {
  constructor(
    public id?: number,
    public fullName?: string,
    public fatherName?: string,
    public husbandName?: string,
    public email?: string,
    public mobile?: string,
    public otherContactDetails?: string,
    public cnic?: string,
    public homeAddress?: string,
    public area?: string,
    public city?: string,
    public postcode?: string,
    public country?: string,
    public status?: SupportStatus
  ) {}
}
