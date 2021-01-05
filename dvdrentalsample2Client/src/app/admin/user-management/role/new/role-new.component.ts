import { Component, OnInit, Inject } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { FormBuilder, Validators } from '@angular/forms';
import { MatDialogRef, MatDialog, MAT_DIALOG_DATA } from '@angular/material/dialog';

import { RoleService } from '../role.service';
import { IRole } from '../irole';
import { Globals, BaseNewComponent, PickerDialogService, ErrorService } from 'src/app/common/shared';
import { GlobalPermissionService } from 'src/app/core/global-permission.service';

@Component({
  selector: 'app-role-new',
  templateUrl: './role-new.component.html',
  styleUrls: ['./role-new.component.scss'],
})
export class RoleNewComponent extends BaseNewComponent<IRole> implements OnInit {
  title: string = 'New Role';
  constructor(
    public formBuilder: FormBuilder,
    public router: Router,
    public route: ActivatedRoute,
    public dialog: MatDialog,
    public dialogRef: MatDialogRef<RoleNewComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any,
    public global: Globals,
    public pickerDialogService: PickerDialogService,
    public roleService: RoleService,
    public errorService: ErrorService,
    public globalPermissionService: GlobalPermissionService
  ) {
    super(formBuilder, router, route, dialog, dialogRef, data, global, pickerDialogService, roleService, errorService);
  }

  ngOnInit() {
    this.entityName = 'Role';
    this.setAssociations();
    super.ngOnInit();
    this.setForm();
    this.checkPassedData();
    this.setPickerSearchListener();
  }

  setForm() {
    this.itemForm = this.formBuilder.group({
      displayName: ['', Validators.required],
      name: ['', Validators.required],
    });

    this.fields = [
      {
        name: 'displayName',
        label: 'display Name',
        isRequired: true,
        isAutoGenerated: false,
        type: 'string',
      },
      {
        name: 'id',
        label: 'id',
        isRequired: true,
        isAutoGenerated: true,
        type: 'number',
      },
      {
        name: 'name',
        label: 'name',
        isRequired: true,
        isAutoGenerated: false,
        type: 'string',
      },
    ];
  }

  setAssociations() {
    this.associations = [];
    this.parentAssociations = this.associations.filter((association) => {
      return !association.isParent;
    });
  }

  onSubmit() {
    let role = this.itemForm.getRawValue();
    super.onSubmit(role);
  }
}