import { Component } from '@angular/core';

@Component({
    moduleId: module.id.toString(),
    templateUrl: './autom.component.html',
    styleUrls: ['./autom.component.css']
})

export class AutomComponent {

    title="Cancelación automática y Cierre diario";
    param_code: string;

    show_read_autom_html=true;
    show_config_autom_html=false;

    showReadAutoms(event: any){        
        this.title=event.title;
    
        this.hideAll_Html();
        this.show_read_autom_html=true;
    }    

    showConfigAutoms(event: any){   
        this.title=event.title;
        this.param_code=event.param_code;
    
        this.hideAll_Html();
        this.show_config_autom_html=true;
    }    

    hideAll_Html(){
        this.show_read_autom_html=false;
        this.show_config_autom_html=false;
    }     
}