import { NgModule } from "@angular/core";
import {TableModule} from 'primeng/table';
import {SliderModule} from 'primeng/slider';
import {DialogModule} from 'primeng/dialog';
import {MultiSelectModule} from 'primeng/multiselect';
import {ContextMenuModule} from 'primeng/contextmenu';
import {DropdownModule} from 'primeng/dropdown';
import { CalendarModule } from "primeng/calendar";
import { InputTextModule } from "primeng/inputtext";
import { ButtonModule } from "primeng/button";
import {ProgressBarModule} from 'primeng/progressbar';

@NgModule({
    declarations: [],
    exports: [
        TableModule,
        CalendarModule,
		SliderModule,
		DialogModule,
		MultiSelectModule,
		ContextMenuModule,
		DropdownModule,
		ButtonModule,
        InputTextModule,
        ProgressBarModule,
    ],
})
export class PrimeNgModule {}