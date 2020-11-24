import { Component, OnInit, Input, Output, EventEmitter, AfterViewInit, ViewChild } from '@angular/core';
import { FormGroup, FormControl, Validators, FormBuilder } from '@angular/forms';
import { AutomJobsService, PagerService } from '../../_services';
import { Observable } from 'rxjs/Observable';
import { AutomJobsHist } from '../../_models/index';
import { DaterangepickerConfig } from 'ng2-daterangepicker';
import { DaterangePickerComponent } from 'ng2-daterangepicker';
import { DatePipe } from '@angular/common';
import * as moment from 'moment';
import { AlertService } from '../../_services/index';

@Component({
    selector: 'app-read-autom',
    templateUrl: './read-autom.component.html',
    styleUrls: ['./read-autom.component.css'],
    providers: [AutomJobsService]
})

export class ReadAutomComponent implements OnInit, AfterViewInit {

    @ViewChild(DaterangePickerComponent)
    private picker: DaterangePickerComponent;   

    public daterange: any = {};
    public dateparam: any = {};

    // see original project for full list of options
    // can also be setup using the config service to apply to multiple pickers
    public options: any = {
        locale: { format: 'DD-MM-YYYY' },
        alwaysShowCalendars: false,
    };

    public selectedDate(value: any) {        
        var start = moment(value.start);     
        var oneMonthAhead = value.start.add(30, 'days');    
        var today = moment();
        var end = moment(this.datePipe.transform(value.end,"yyyy-MM-dd"));
        debugger;
        if(end <= oneMonthAhead && end <= today) {

            this.daterange.start = this.datePipe.transform(start,"dd-MM-yyyy");
            this.dateparam.start = this.daterange.start;

            this.daterange.end = this.datePipe.transform(value.end.add(1, 'days'),"dd-MM-yyyy");
            this.dateparam.end = this.datePipe.transform(end,"dd-MM-yyyy");

            this.picker.datePicker.setStartDate(this.dateparam.start);
            this.picker.datePicker.setEndDate(this.dateparam.end);
            //debugger;
            // se realiza la busqueda de los datos en el rango seleccionado
            this.automJobsService.readAutomJobsByDateRange(this.daterange.start,this.daterange.end)
                .subscribe(automJobsHist => {
                    //debugger;
                    this.automJobsHist=automJobsHist;
                    this.alertService.error(""); // terminar de desaparecer el mg de error
                    this.setPage(1);
                },
                error => {
                    this.alertService.error(error);
                });               
        }
        else {

            this.picker.datePicker.setStartDate(this.dateparam.start);
            this.picker.datePicker.setEndDate(this.dateparam.end);         
            
            // se manda mensaje de error de que el rango no puede superar 30 dias   
            this.alertService.error("El rango de fechas no puede ser superior a 30 dias ni debe exceder la fecha actual");
        }

        //this.daterange.label = value.label;
    }    

    @Output() show_config_autom_event=new EventEmitter();    

    search_automjobs_form: FormGroup;

    automJobsHist: AutomJobsHist[];

    pager: any = {};

    pagedItems: any[];       

    constructor(private automJobsService: AutomJobsService,
                private pagerService: PagerService,
                formBuilder: FormBuilder,
                private daterangepickerOptions: DaterangepickerConfig,
                private datePipe: DatePipe,
                private alertService: AlertService){                    

    }

    updateConfig(code: string){
        
        // tell the parent component (AppComponent)
        this.show_config_autom_event.emit({
            param_code: code,
            title: "Configuración"            
        });
    }  

    addDays(theDate, days) {
        return new Date(theDate.getTime() + days*24*60*60*1000);
    }

    ngAfterViewInit() {        
        var now = new Date();
        var oneMonthAgo = this.addDays(now,-30);                
        this.dateparam.end = this.datePipe.transform(now,"dd-MM-yyyy");
        this.dateparam.start = this.datePipe.transform(oneMonthAgo,"dd-MM-yyyy");
        this.picker.datePicker.setStartDate(this.dateparam.start);
        this.picker.datePicker.setEndDate(this.dateparam.end);
    }

    ngOnInit(){           

        this.automJobsService.readAutomJobs()
            .subscribe(automJobsHist => {
                this.automJobsHist=automJobsHist;

                this.setPage(1);
            });            
    }

    setPage(page: number) {
        
        if (page < 1 || page > this.pager.totalPages) {
            return;
        }

        // get pager object from service
        this.pager = this.pagerService.getPager(this.automJobsHist.length, page);

        // get current page of items
        this.pagedItems = this.automJobsHist.slice(this.pager.startIndex, this.pager.endIndex + 1);
    }     
}