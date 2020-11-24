import { Component, OnChanges, Input, Output, EventEmitter } from '@angular/core';
import { FormGroup, FormControl, Validators, FormBuilder } from '@angular/forms';
import { ParamService } from '../../_services/param.service';
import { OperationParam } from '../../_models/index';

@Component({
    selector: 'app-config-autom',
    templateUrl: './config-autom.component.html',
    styleUrls: ['./config-autom.component.css'],
    providers: [ParamService]
})
export class ConfigAutomComponent implements OnChanges {    

    config_autom_form: FormGroup;

    @Output() show_read_autom_event = new EventEmitter();

    @Input() param_code: any;    

    param: OperationParam;

    constructor(
        private paramService: ParamService,
        private formBuilder: FormBuilder
    )
    {
        this.config_autom_form = this.formBuilder.group({
            id: ["", Validators.required],
            code: ["", Validators.required],
            name: ["", Validators.required],
            operation_value: ["", Validators.required],
            comments: ["", Validators.required]
        });
    }

    updateOperationParam(){

        //this.update_merchant_form.value.merchant_id = this.merchant_id;
        
        // send data to server
        this.paramService.updateOperationParam(this.config_autom_form.value)
            .subscribe(
                 param => {
                    // go back to list of automs
                    this.readAutoms();
                 },
                 error => console.log(error)
             );
    }    

    readAutoms(){
        
        this.show_read_autom_event.emit({ 
            title: "Cancelación automática y Cierre diario" 
        });
    }    

    // call the record when 'param_code' was changed
    ngOnChanges(){
        
        this.paramService.readOperationParamByCode(this.param_code)
            .subscribe(param => {
                // put values in the form
                this.config_autom_form.patchValue({
                    id: param.id,
                    code: param.code,
                    name: param.name,
                    operation_value: param.operation_value,
                    comments: param.comments
                });                
            });
    }

}