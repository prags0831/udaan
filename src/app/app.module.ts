import { NgModule } from '@angular/core';
import { HttpClientModule} from '@angular/common/http';

import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomeComponent } from './home/home.component';
import { AdminComponent } from './admin/admin.component';
import { UserComponent } from './user/user.component';
import { LoginComponent } from './login/login.component';
import { HeaderComponent } from './header/header.component';
import { FooterComponent } from './footer/footer.component';
import { ForbiddenComponent } from './forbidden/forbidden.component';
import { RegistrationComponent } from './registration/registration.component';
import { ContactUsComponent } from './contact-us/contact-us.component';
import { SidebarComponent } from './sidebar/sidebar.component';
import { AboutComponent } from './about/about.component';
import { AllFlightComponent } from './all-flight/all-flight.component';
import { BookComponent } from './book/book.component';
import { CancelComponent } from './cancel/cancel.component';
import { CheckinComponent } from './checkin/checkin.component';
import { DetailsComponent } from './details/details.component';
import { EditFlightComponent } from './edit-flight/edit-flight.component';
import { FoodComponent } from './food/food.component';
import { PaymentComponent } from './payment/payment.component';
import { SeatmatrixComponent } from './seatmatrix/seatmatrix.component';
import { ViewBookdetailsComponent } from './view-bookdetails/view-bookdetails.component';
import { PaymentEmailComponent } from './payment-email/payment-email.component';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    AdminComponent,
    UserComponent,
    LoginComponent,
    HeaderComponent,
    FooterComponent,
    ForbiddenComponent,
    RegistrationComponent,
    ContactUsComponent,
    SidebarComponent,
    AboutComponent,
    AllFlightComponent,
    BookComponent,
    CancelComponent,
    CheckinComponent,
    DetailsComponent,
    EditFlightComponent,
    FoodComponent,
    PaymentComponent,
    SeatmatrixComponent,
    ViewBookdetailsComponent,
    PaymentEmailComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
