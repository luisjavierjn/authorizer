import { Routes, RouterModule } from '@angular/router';

import { HomeComponent } from './home/index';
import { AuthGuard } from './_guards/index';
import { LoginComponent } from './login/index';
import { RegisterComponent } from './register/index';
import { UsersComponent } from './users/index';
import { MerchantsComponent } from './merchant/index';
import { UnifiedComponent } from './unified/index';
import { AutomComponent } from './autom/index';
import { TransactionsComponent } from './transaction/index';

const appRoutes: Routes = [
    { path: '', component: HomeComponent, canActivate: [AuthGuard] },    
    { path: 'login', component: LoginComponent },
    { path: 'register', component: RegisterComponent },
    { path: 'users', component: UsersComponent },
    { path: 'merchants', component: MerchantsComponent },
    { path: 'unified', component: UnifiedComponent },
    { path: 'autom', component: AutomComponent },
    { path: 'transaction', component: TransactionsComponent },

    // otherwise redirect to home
    { path: '**', redirectTo: '' }
];

export const routing = RouterModule.forRoot(appRoutes);