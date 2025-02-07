import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './pages/home/home.component';
import { ContatoFormComponent } from './pages/contato-form/contato-form.component';

export const routes: Routes = [
    {
        path: "form",
        component: ContatoFormComponent
    },
    {
        path: "form/:id",
        component: ContatoFormComponent
    },
    {
        path: "home",
        component: HomeComponent,
    },
   
    {
        path: '**',
        redirectTo: 'home'
    }
];

@NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule]
})
export class AppRoutesModule { }