import { CommonModule } from '@angular/common';
import { Component, Input } from '@angular/core';
import { ItemComponent } from "../item/item.component";
import { Contato } from '../../shared/model/contato.model';

@Component({
  selector: 'app-session',
  standalone: true,
  imports: [CommonModule, ItemComponent],
  templateUrl: './session.component.html',
  styleUrl: './session.component.css'
})
export class SessionComponent {

  private _letter: string = ""
  @Input() items:  Contato[] = []

  
  @Input()  public set letter(v : string) {
    this._letter= v;
  }

  
  public get letter() : string {
    return this._letter.toUpperCase()
  }
  
  

}
