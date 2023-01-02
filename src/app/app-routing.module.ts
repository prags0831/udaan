import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AboutComponent } from './about/about.component';
import { AdminComponent } from './admin/admin.component';
import { AllFlightComponent } from './all-flight/all-flight.component';
import { BookComponent } from './book/book.component';
import { CancelComponent } from './cancel/cancel.component';
import { CheckinComponent } from './checkin/checkin.component';
import { ContactUsComponent } from './contact-us/contact-us.component';
import { DetailsComponent } from './details/details.component';
import { EditFlightComponent } from './edit-flight/edit-flight.component';
import { FoodComponent } from './food/food.component';
import { ForbiddenComponent } from './forbidden/forbidden.component';
import { HomeComponent } from './home/home.component';
import { LoginComponent } from './login/login.component';
import { PaymentEmailComponent } from './payment-email/payment-email.component';
import { PaymentComponent } from './payment/payment.component';
import { RegistrationComponent } from './registration/registration.component';
import { SeatmatrixComponent } from './seatmatrix/seatmatrix.component';
import { UserComponent } from './user/user.component';
import { ViewBookdetailsComponent } from './view-bookdetails/view-bookdetails.component';

import { AuthGuard } from './_auth/auth.guard';

const routes: Routes = [
  {path:'',redirectTo:'home',pathMatch:'full'},
  {path:'home',component:HomeComponent,pathMatch:'full'},
  { path: 'addflight', component: AdminComponent, canActivate:[AuthGuard], data:{roles:['Admin']} },
  { path: 'viewflight', component: UserComponent ,  canActivate:[AuthGuard], data:{roles:['User']} },
  { path: 'login', component: LoginComponent,pathMatch:'full' },
  {path:'payment/:fare', component:PaymentComponent},
  {path:'book', component:BookComponent, pathMatch:'full'},
  {path:'cancel', component:CancelComponent, pathMatch:'full'},
  {path:'book/:flight_id', component:BookComponent},
  {path:'food', component:FoodComponent, pathMatch:'full'},
  {path:'checkin', component:CheckinComponent, pathMatch:'full'},
  {path:'registration', component:RegistrationComponent, pathMatch:'full'},
  {path:'about', component:AboutComponent, pathMatch:'full'},
  {path:'seat', component:SeatmatrixComponent, pathMatch:'full'},
  {path:'payment', component:PaymentComponent, pathMatch:'full'},
  {path:'paymentsuccess', component:PaymentEmailComponent, pathMatch:'full'},
  {path:'contactus', component:ContactUsComponent, pathMatch:'full'},
  { path: 'forbidden', component: ForbiddenComponent },
  {path:'flightedit/:flight_id', component:EditFlightComponent, pathMatch:'full'},
  {path:'allflight', component:AllFlightComponent, pathMatch:'full'},
  {path:'viewbookdetails', component:ViewBookdetailsComponent,pathMatch:'full'},
  {path:'details/:email/:flight_id', component:DetailsComponent}
];


@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
