import { Component, OnInit } from '@angular/core';
import { ItemComponent } from "../../components/item/item.component";
import { CommonModule, getLocaleExtraDayPeriodRules } from '@angular/common';
import { SessionComponent } from "../../components/session/session.component";
import { ItemListContato, ListContato, SimpleContato } from '../../shared/model/contato.model';
import { ContatoService } from '../../shared/service/contato.service';
import { HttpClientModule } from '@angular/common/http';
import { MatButtonModule } from '@angular/material/button';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [CommonModule, MatButtonModule, RouterModule, ItemComponent, SessionComponent],
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})
export class HomeComponent implements OnInit {

  private list: ListContato | null = null;

  constructor(private contatoService: ContatoService) {
  }

  ngOnInit(): void {
    this.findAll()
  }

  public get favoritos(): SimpleContato[] {
    return this.list?.favoritos ? this.list.favoritos : []
  }

  public get letras(): ItemListContato[] {
    return this.list?.letras ? this.list.letras : []
  }

  private findAll() {
    this.contatoService.findAll().subscribe(res => {
      this.list = res
    })
  }
}
