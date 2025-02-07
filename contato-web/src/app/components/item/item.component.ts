import { Component, Input } from '@angular/core';
import { SimpleContato } from '../../shared/model/contato.model';
import { RouterModule } from '@angular/router';
import { MatIconModule } from '@angular/material/icon';
import { CommonModule } from '@angular/common';
import { ContatoService } from '../../shared/service/contato.service';

@Component({
  selector: 'app-item',
  standalone: true,
  imports: [RouterModule, CommonModule, MatIconModule],
  templateUrl: './item.component.html',
  styleUrl: './item.component.css'
})
export class ItemComponent {
  private _contato: SimpleContato | null = null

  constructor(private contatoService: ContatoService) { }

  public get contato(): SimpleContato | null {
    return this._contato
  }

  @Input() public set contato(v: SimpleContato) {
    this._contato = v;
  }


  public get favorito(): boolean {
    return this._contato?.snFavorito ?? true
  }

  toggleFavorite(event: Event) {
    event.stopPropagation();

    if (this.favorito) {
      this.contatoService.unFavorite(this.contato?.id!!).subscribe(res => console.log("Contato desfavoritado com sucesso!"))
    } else {
      this.contatoService.favorite(this.contato?.id!!).subscribe(res => console.log("Contato favoritado com sucesso!"))
    }

    window.location.reload();

  }
}
