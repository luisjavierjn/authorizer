import { NgModule, APP_INITIALIZER, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule, ReactiveFormsModule }    from '@angular/forms';
import { HttpModule } from '@angular/http';
import { Daterangepicker } from 'ng2-daterangepicker'
import { DatePipe } from '@angular/common';
import { Http } from '@angular/http';

// used to create fake backend
import { fakeBackendProvider } from './_helpers/index';
import { MockBackend, MockConnection } from '@angular/http/testing';
import { BaseRequestOptions } from '@angular/http';

import { AppComponent }  from './app.component';
import { routing }        from './app.routing';

import { AlertComponent } from './_directives/index';
import { AuthGuard } from './_guards/index';
import {    AlertService, 
            AuthenticationService, 
            UserService, 
            RoleService,
            MerchantService,
            ChannelService,
            FileService,
            AutomJobsService,
            PagerService,
            TransactionService,
            ConfigService
        } from './_services/index';
import { HomeComponent } from './home/index';
import { MenuComponent } from './menu/index';
import { LoginComponent } from './login/index';
import { RegisterComponent } from './register/index';
import { UsersComponent } from './users/index';
import { ReadUsersComponent } from './users/read/read-users.component';
import { ReadOneUserComponent } from './users/read-one/read-one-user.component';
import { CreateUserComponent } from './users/create/create-user.component';
import { UpdateUserComponent } from './users/update/update-user.component';
import { DeleteUserComponent } from './users/delete/delete-user.component';
import { MerchantsComponent } from './merchant/index';
import { ReadMerchantsComponent } from './merchant/read/read-merchants.component';
import { ReadOneMerchantComponent } from './merchant/read-one/read-one-merchant.component';
import { CreateMerchantComponent } from './merchant/create/create-merchant.component';
import { UpdateMerchantComponent } from './merchant/update/update-merchant.component';
import { DeleteMerchantComponent } from './merchant/delete/delete-merchant.component';
import { UnifiedComponent } from './unified/index';
import { ReadUnifiedComponent } from './unified/read/read-unified.component';
import { AutomComponent } from './autom/index';
import { ReadAutomComponent } from './autom/read/read-autom.component';
import { ConfigAutomComponent } from './autom/config/config-autom.component';
import { TransactionsComponent } from './transaction/index';
import { ReadTransactionComponent } from './transaction/read/read-transaction.component';

@NgModule({
    imports: [
        BrowserModule,
        FormsModule,
        HttpModule,
        ReactiveFormsModule,
        routing,
        Daterangepicker        
    ],
    declarations: [
        AppComponent,
        AlertComponent,
        HomeComponent,
        MenuComponent,
        LoginComponent,
        RegisterComponent,
        UsersComponent,
        ReadUsersComponent,
        ReadOneUserComponent,
        CreateUserComponent,
        UpdateUserComponent,
        DeleteUserComponent,
        MerchantsComponent,
        ReadMerchantsComponent,
        ReadOneMerchantComponent,
        CreateMerchantComponent,
        UpdateMerchantComponent,
        DeleteMerchantComponent,
        UnifiedComponent,
        ReadUnifiedComponent,
        AutomComponent,
        ReadAutomComponent,
        ConfigAutomComponent,
        TransactionsComponent,
        ReadTransactionComponent
    ],
    providers: [
        AuthGuard,
        AlertService,
        AuthenticationService,
        UserService,
        RoleService,
        MerchantService,
        ChannelService,
        FileService,
        AutomJobsService,
        PagerService,
        DatePipe,
        TransactionService,
        ConfigService,
        {
            provide: APP_INITIALIZER,
            useFactory: initConfiguration,
            deps: [ConfigService],
            multi: true
        }
        // providers used to create fake backend
        //fakeBackendProvider,
        //MockBackend,
        //BaseRequestOptions
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA],
    bootstrap: [AppComponent]
})

export class AppModule { }

export function initConfiguration(config: ConfigService) {
    return () => config.load();
};