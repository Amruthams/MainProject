"""Edulink URL Configuration

The `urlpatterns` list routes URLs to views. For more information please see:
    https://docs.djangoproject.com/en/3.2/topics/http/urls/
Examples:
Function views
    1. Add an import:  from my_app import views
    2. Add a URL to urlpatterns:  path('', views.home, name='home')
Class-based views
    1. Add an import:  from other_app.views import Home
    2. Add a URL to urlpatterns:  path('', Home.as_view(), name='home')
Including another URLconf
    1. Import the include() function: from django.urls import include, path
    2. Add a URL to urlpatterns:  path('blog/', include('blog.urls'))
"""
from django.contrib import admin
from django.urls import path

from EdulinkApp import views

urlpatterns = [

    path('home/', views.home),
    path('dashboard/', views.dashboard),

    path("login/",views.login_func),
    path("login_post/",views.login_post),


    path("adddept/",views.adddept),
    path("adddept_post/",views.adddept_post),
    path("admincheckdept/",views.admincheckdept),
    path("viewdept/",views.viewdept),
    path("editdept/<str:did>",views.editdept),
    path("editdept_post/",views.editdept_post),
    path("deletedept/<str:did>",views.deletedept),

    path("addprogram/", views.addprogram),
    path("addprogram_post/", views.addprogram_post),
    path("admincheckpgm/",views.admincheckpgm),
    path("viewprogram/", views.viewprogram),
    path("editprogram/<str:did>", views.editprogram),
    path("editprogram_post/", views.editprogram_post),
    path("deleteprogram/<id>", views.deleteprogram),

    path("addbatch/", views.addbatch),
    path("addbatch_post/", views.addbatch_post),
    # path("admincheckbatch/", views.admincheckbatch),
    path("viewbatch/",views.viewbatch),
    path("editbatch/<str:did>", views.editbatch),
    path("editbatch_post/", views.editbatch_post),
    path("deletebatch/<id>", views.deletebatch),


    path("addfaculty/",views.addfaculty),
    path("addfaculty_post/",views.addfaculty_post),
    path("viewfaculty/",views.viewfaculty),
    path("editfaculty/<str:did>",views.editfaculty),
    path("editfaculty_post/",views.editfaculty_post),
    path("deletefaculty/<id>",views.deletefaculty),


    path("assignstaff/",views.assignstaff),
    path("assignstaff_post/",views.assignstaff_post),
    path("viewstaff/",views.viewstaff),
    path("editstaff/<str:did>",views.editstaff),
    path("editstaff_post/",views.editstaff_post),
    path("deletestaff/<id>",views.deletestaff),
    path('get_staffs/', views.get_staffs, name='get_staffs'),

    path("chngpwd/",views.chngpwd),
    path("chngpwd_post/",views.chngpwd_post),

    path("notification/",views.notifi),
    path("notification_post/",views.notification_post),
    path("viewnotification/",views.viewnotification),
    path("viewnotification_post/",views.viewnotification_post),
    path("deletenotification/<id>",views.deletenotification),


    path("changevalues/<id>",views.changevalue),

    path("viewnotification/",views.viewnotification),

    path("viewstudents/",views.viewstudents),


    path("calendar/",views.calendarm),
    path("addevent/<date>/<event>/<day>/",views.addevent),
    path("view_allevent/<date>/<event>/<day>/",views.addevent),






    path("calendars/",views.calendars),





#ANDROID


#Second Sprint
    path("and_login/",views.and_login),
    path("view_profile/",views.view_profile),
    path("student_viewprofile/",views.student_viewprofile),
    path("changepassword/",views.changepassword),

    path("addsubject/",views.addsubject),
    path("and_view_programs/",views.and_view_programs),
    path("and_view_subjects/",views.and_view_subjects),
    path("and_view_subjects_sem1/",views.and_view_subjects_sem1),
    path("and_view_subjects_sem/",views.and_view_subjects_sem),
    path("hod_delete_subject/",views.hod_delete_subject),



    path("and_view_faculty/",views.and_view_faculty),
    path("addsubjectallocation/",views.addsubjectallocation),
    path("and_view_subjectallocation/",views.and_view_subjectallocation),
    path("and_view_subjectallocation_sem/",views.and_view_subjectallocation_sem),


    path("tutor_viewclass/",views.tutor_viewclass),
    path("tutor_add_student/",views.tutor_add_student),
    path("tutor_viewstudents/",views.tutor_viewstudents),
    path("tutor_update_student/",views.tutor_update_student),
    path("tutor_update_student_post/",views.tutor_update_student_post),
    path("teacher_view_all_student/",views.teacher_view_all_student),
    path("teacher_view_all_student_post/",views.teacher_view_all_student_post),





    path("add_timetable/",views.add_timetable),
    path("show_courses/",views.show_courses),
    path("show_sem/",views.show_sem),
    path("show_subject/",views.show_subject),
    path("search_subject/",views.search_subject),
    path("show_program/",views.show_program),
    path("hod_view_timetable/",views.hod_view_timetable),
    path("teacher_view_timetable/",views.teacher_view_timetable),
    # path("teacher_viewtimetable/",views.teacher_viewtimetable),
    path("teacher_view_subjects/",views.teacher_view_subjects),
    path("teacher_view_subjects_sem_wise/",views.teacher_view_subjects_sem_wise),
    path("show_dept/",views.show_dept),
    path("show_students/",views.show_students),


    path("teacher_attendance/",views.teacher_attendance),
    path("teacher_add_attendance/",views.teacher_add_attendance),
    path("teacher_view_attendance/",views.teacher_view_attendance),
    path("teacher_view_attendance_percentage/",views.teacher_view_attendance_percentage),
    path("teacher_view_attendance_overall/",views.teacher_view_attendance_overall),
    path("students_view_attendance/", views.students_view_attendance),

    path("teacher_add_substitution/",views.teacher_add_substitution),
    path("teacher_add_substitution_post/",views.teacher_add_substitution_post),
    path("teacher_view_substitution/",views.teacher_view_substitution),
    path("teacher_view_substitution_search/",views.teacher_view_substitution_search),
    path("view_substitution/",views.view_substitution),



    path("teacher_add_exam/",views.teacher_add_exam),
    path("teacher_add_internal/",views.teacher_add_internal),
    path("teacher_delete_internal/",views.teacher_delete_internal),
    path("show_internal_mark/",views.show_internal_mark),
    path("students_view_internal/",views.students_view_internal),

#====medical leave==========

    path("student_request_leave/",views.student_request_leave),
    path("student_view_leave/",views.student_view_leave),
    path("tutor_medical_leave/",views.tutor_medical_leave),
    path("student_upload_medicalrep/",views.student_upload_medicalrep),
    path("tutor_approve_leave/",views.tutor_approve_leave),
    path("tutor_reject_leave/",views.tutor_reject_leave),


 #=========studymaterial========

    path("teacher_upload_study_material/",views.teacher_upload_study_material),
    path("teacher_view_studymaterial/",views.teacher_view_studymaterial),
    path("teacher_delete_studymaterial/",views.teacher_delete_studymaterial),



    path("teacher_add_assignment/",views.teacher_add_assignment),
    path("student_assignment/",views.student_assignment),
    path("student_upload_assignment/",views.student_upload_assignment),
    path("teacher_view_assignment/",views.teacher_view_assignment),
    path("teacher_view_assignment_uploads/",views.teacher_view_assignment_uploads),
    path("in_message2/",views.in_message2),
    path("view_message2/",views.view_message2),
    path("in_message/", views.in_message),
    path("view_message/", views.view_message),




    path("andnotification/", views.andnotification),







    path("calender_get/",views.calender_get),
    path("get/",views.get),
    path("save/",views.save),
    path("delete/",views.delete),










]
