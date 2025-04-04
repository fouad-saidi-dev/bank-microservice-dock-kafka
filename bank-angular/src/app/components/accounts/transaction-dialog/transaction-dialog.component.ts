import {Component, Inject} from '@angular/core';
import {MAT_DIALOG_DATA} from "@angular/material/dialog";
import {Router} from "@angular/router";

@Component({
  selector: 'app-transaction-dialog',
  templateUrl: './transaction-dialog.component.html',
  styleUrl: './transaction-dialog.component.css'
})
export class TransactionDialogComponent {

  constructor(@Inject(MAT_DIALOG_DATA) public data: any, private router:Router) {}

  saveTransfer() {
    this.router.navigate(['/admin/new-transfer'])
  }

  goToTrx(beneficiaryId: string) {
    this.router.navigateByUrl(`/admin/new-transfer/${beneficiaryId}`)
  }
}
