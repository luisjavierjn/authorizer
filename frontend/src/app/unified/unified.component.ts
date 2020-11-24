import { Component } from '@angular/core';

@Component({
    moduleId: module.id.toString(),
    templateUrl: './unified.component.html',
    styleUrls: ['./unified.component.css']
})

export class UnifiedComponent {
    // properties for child components
    title="Archivos de Conciliación";
    batch_id: number;    

    // properties used to identify what views to show
    show_read_unified_html=true;
    show_read_one_unified_html=false;

    // show unified list
    showReadUnified(event: any){        
        // set title
        this.title=event.title;
    
        // hide all html then show only one html
        this.hideAll_Html();
        this.show_read_unified_html=true;
    }    

    // show details of a unified
    showReadOneUnified(event: any){
    
        // set title and user ID
        this.title=event.title;
        this.batch_id=event.batch_id;
    
        // hide all html then show only one html
        this.hideAll_Html();
        this.show_read_one_unified_html=true;
    }    

    // hide all html views
    hideAll_Html(){
        this.show_read_unified_html=false;
        this.show_read_one_unified_html=false;
    }    
}