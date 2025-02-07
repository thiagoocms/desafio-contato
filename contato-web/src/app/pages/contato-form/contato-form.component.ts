import { Component } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { ActivatedRoute, Router, RouterModule } from '@angular/router';
import { ContatoService } from '../../shared/service/contato.service';
import { CommonModule } from '@angular/common';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { ToastrModule } from 'ngx-toastr';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-contato-form',
  standalone: true,
  imports: [CommonModule,
    ReactiveFormsModule,
    RouterModule,
    MatCardModule,
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule,
    ToastrModule],
  templateUrl: './contato-form.component.html',
  styleUrl: './contato-form.component.css'
})

export class ContatoFormComponent {

  contatoForm: FormGroup;
  private _isEditMode = false;
  private contatoId: number | null = null;

  constructor(
    private fb: FormBuilder,
    private route: ActivatedRoute,
    private router: Router,
    private contatoService: ContatoService,
    private toast: ToastrService
  ) {
    this.contatoForm = this.fb.group({
      id: [null],
      nome: ['', [Validators.required, Validators.minLength(3)]],
      email: ['', [Validators.required, Validators.email]],
      celular: ['', Validators.required],
      telefone: ['']
    });
  }

  ngOnInit() {
    this.route.paramMap.subscribe(params => {
      const id = Number(params.get('id'));
      if (id) {
        this._isEditMode = true;
        this.contatoId = +id;
       this.findById()
      } else {
        this._isEditMode = false;
        this.contatoForm.reset();
      }
    });
  }

  
  public get isEditMode() : boolean {
    return this._isEditMode;
  }
  

  onSubmit() {
    if (this.contatoForm.valid) {
      if (this._isEditMode) {
        this.contatoService.update(this.contatoId!!, this.contatoForm.value).subscribe(() =>  {
          this.toast.success('Contato atualizado com sucesso!')
          this.router.navigate(['/'])
         }, err => this.toast.error(err.error.message));
      } else {
       this.contatoService.create(this.contatoForm.value).subscribe(() =>  {
        this.toast.success('Contato criado com sucesso!')
        this.router.navigate(['/'])
       }, err => this.toast.error(err.error.message));
      }
  
    }
  }

  onDeactivate() {
    if (this.contatoId) {
      this.contatoService.deactivate(this.contatoId).subscribe(() => {
        this.toast.success('Contato desativado com sucesso!');
        this.router.navigate(['/']);
      }, err => this.toast.error(err.error.message));
    }
  }
  

  private findById() {
    this.contatoService.findById(this.contatoId!!)
    .subscribe(res => this.contatoForm.patchValue(res));
  }

}
