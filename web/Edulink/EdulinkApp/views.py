from datetime import datetime
from stat import ST_UID

import sys
from django.http import JsonResponse
from django.shortcuts import get_object_or_404
import datetime
import EdulinkApp.S2_lib as evt

from django.core.files.storage import FileSystemStorage
from django.http import HttpResponse, JsonResponse
from django.shortcuts import render, redirect
from EdulinkApp.models import *
from calendar import monthrange
# Create your views here.


def login_func(request):
    return render(request,"loginindex.html")

def home(request):
    return render(request,'adminindex.html')

def dashboard(request):
    return render(request,'admindash.html')


def login_post(request):
    username=request.POST["textfield"]
    password=request.POST["textfield2"]
    import hashlib
    passwords = password
    result = hashlib.sha256(passwords.encode())
    if login.objects.filter(username=username, password=result.hexdigest()).exists():
        d = login.objects.get(username=username, password=result.hexdigest())
        request.session["lid"] = d.id
        if d.type == "admin":
            return redirect('/myapp/dashboard/')
        else:
            return HttpResponse('''<script>alert('invalid');window.location='/myapp/login/'</script>''')
    return HttpResponse('''<script>alert('invalid');window.location='/myapp/login/'</script>''')



def adddept(request):
    return render(request,"adddept.html")

def adddept_post(request):
    dept=request.POST["textfield"]
    deptobj = department()
    deptobj.department = dept
    deptobj.save()
    return redirect('/myapp/viewdept/#about')


def admincheckdept(request):
    c=request.GET["c"]
    print(c)
    h=department.objects.filter(department=c)
    if h.exists():
        return JsonResponse({'status':'no'})
    else:
        return JsonResponse({'status':'ok'})

def viewdept(request):
    res=department.objects.all()
    return render(request,"viewdept.html",{'data':res})

def editdept(request,did):
    res = department.objects.get(id=did)
    return render(request,"editdept.html", {'data':res})

def editdept_post(request):
    did = request.POST['h1']
    dept = request.POST["textfield"]
    res=department.objects.filter(pk=did).update(department=dept)
    # deptobj = department()
    # deptobj.department = dept
    # deptobj.save()
    return redirect('/myapp/viewdept/#about')

def deletedept(request,did):
    res=department.objects.get(id=did).delete()
    # return redirect('/myapp/viewdept/')
    return HttpResponse('''<script>alert("Are you sure!");window.location="/myapp/viewdept/#about"</script>''')


def addprogram(request):
    res = department.objects.all()
    return render(request,"addprogram.html",{'data':res})

def addprogram_post(request):
    pgm = request.POST["textfield"]
    sem=request.POST["textfield2"]
    dept = request.POST["textfield3"]
    obj = program()
    obj.pname = pgm
    obj.sem = sem
    res = department.objects.get(id=dept)
    obj.DEPARTMENT = res
    obj.save()
    return redirect('/myapp/viewprogram/#about')

def admincheckpgm(request):
    c=request.GET["c"]
    print(c)
    h=program.objects.filter(pname=c)
    if h.exists():
        return JsonResponse({'status':'no'})
    else:
        return JsonResponse({'status':'ok'})

def viewprogram(request):
    res=program.objects.all()
    return render(request,"viewprogram.html",{'data':res})

def editprogram(request,did):
    res =program.objects.get(id=did)
    res2=department.objects.all()
    print(res2)
    return render(request,"editprogram.html",{'data':res,'data2':res2})

def editprogram_post(request):
    did = request.POST['h1']
    name = request.POST["textfield"]
    sem=request.POST["textfield2"]
    department=request.POST["textfield3"]
    res=program.objects.filter(pk=did).update(DEPARTMENT=department,pname=name,sem=sem)
    return redirect('/myapp/viewprogram/')


def deleteprogram(request,id):
    res=program.objects.get(pk=id).delete()
    # return redirect('/myapp/viewprogram/')
    return HttpResponse('''<script>alert("Are you sure!");window.location="/myapp/viewprogram/#about"</script>''')





def addfaculty(request):
    res = department.objects.all()
    print(res)
    return render(request,"addfaculty.html",{'data':res})

def addfaculty_post(request):
    ktu=request.POST["textfield12"]
    fname=request.POST["textfield"]
    lname=request.POST["textfield2"]
    photo=request.FILES["fileField3"]
    house=request.POST["textfield0"]
    place=request.POST["textfield4"]
    post=request.POST["textfield5"]
    pin=request.POST["textfield6"]
    district=request.POST["textfield7"]
    email=request.POST["textfield8"]
    phno=request.POST["textfield10"]
    dob = request.POST["textfield9"]
    certificate=request.FILES["fileField"]
    dept=request.POST["textfield11"]
    import hashlib
    passwords = ktu
    result = hashlib.sha256(passwords.encode())
    lobj=login()
    lobj.username=email
    lobj.password=result.hexdigest()
    lobj.type="teacher"
    lobj.save()
    obj = faculty()
    obj.ktu_id= ktu
    obj.fname= fname
    obj.lname= lname
    fs=FileSystemStorage()
    s=datetime.now().strftime("%Y%m%d%H%M%S")+".jpg"
    fn=fs.save(s,photo)
    fs=FileSystemStorage()
    date=datetime.now().strftime("%Y%m%d%H%M%S")+certificate.name
    fb=fs.save(date,certificate)
    obj.photo= fs.url(s)
    obj.house= house
    obj.place= place
    obj.post= post
    obj.pin= pin
    obj.district= district
    obj.email= email
    obj.phno= phno
    obj.dob= dob
    obj.certificate= fs.url(date)
    res = department.objects.get(id=dept)
    obj.DEPARTMENT = res
    obj.LOGIN=lobj
    obj.save()
    return redirect('/myapp/viewfaculty/')

def viewfaculty(request):
    res=faculty.objects.order_by('fname')
    return render(request,"viewfaculty.html",{'data':res})

def editfaculty(request,did):
    res = faculty.objects.get(id=did)
    res2 = department.objects.all()
    return render(request,"editfaculty.html",{'data':res,'data2':res2})

def editfaculty_post(request):
    did = request.POST['h1']
    ktu=request.POST["textfield12"]
    fname=request.POST["textfield"]
    lname=request.POST["textfield2"]

    dob=request.POST["textfield9"]
    house=request.POST["textfield0"]
    place=request.POST["textfield4"]
    post=request.POST["textfield5"]
    pin=request.POST["textfield6"]
    dis=request.POST["textfield7"]
    email=request.POST["textfield8"]
    phno=request.POST["textfield10"]
    dept=request.POST["textfield11"]
    if 'fileField3' in request.FILES:
        photo = request.FILES["fileField3"]
        if photo.name !="":
            fs = FileSystemStorage()
            s = datetime.now().strftime("%Y%m%d%H%M%S") + ".jpg"
            fn = fs.save(s, photo)
            res = faculty.objects.filter(pk=did).update(DEPARTMENT=dept,ktu_id=ktu,fname=fname,lname=lname,photo=fs.url(s),house=house,place=place,post=post,pin=pin,district=dis,email=email,phno=phno,dob=dob )
            return redirect('/myapp/viewfaculty/#about')
        else:
            res = faculty.objects.filter(pk=did).update(DEPARTMENT=dept, ktu_id=ktu, fname=fname, lname=lname, house=house, place=place, post=post, pin=pin,district=dis, email=email, phno=phno,dob=dob)
            return redirect('/myapp/viewfaculty/#about')

    if 'fileField' in request.FILES:
        certi = request.FILES["fileField3"]
        if certi.name !="":
            fs = FileSystemStorage()
            date = datetime.now().strftime("%Y%m%d%H%M%S") + certi.name
            fb = fs.save(date, certi)
            res = faculty.objects.filter(pk=did).update(DEPARTMENT=dept,ktu_id=ktu,fname=fname,lname=lname,certificate=fs.url(date),house=house,place=place,post=post,pin=pin,district=dis,email=email,phno=phno,dob=dob )
            return redirect('/myapp/viewfaculty/#about')
        else:
            res = faculty.objects.filter(pk=did).update(DEPARTMENT=dept, ktu_id=ktu, fname=fname, lname=lname, house=house, place=place, post=post,pin=pin, district=dis, email=email, phno=phno, dob=dob)
            return redirect('/myapp/viewfaculty/#about')

    else:
        res = faculty.objects.filter(pk=did).update(DEPARTMENT=dept, ktu_id=ktu, fname=fname, lname=lname,house=house, place=place, post=post, pin=pin, district=dis,email=email, phno=phno, dob=dob)
        return redirect('/myapp/viewfaculty/#about')


def deletefaculty(requset,id):
    res = faculty.objects.get(pk=id).delete()
    return HttpResponse('''<script>alert("Are you sure!");window.location="/myapp/viewfaculty/#about"</script>''')


def addbatch(request):
    res=program.objects.all()
    return render(request,"addbatch.html",{'data':res,"d":range(2019,2030,1)})

def addbatch_post(request):
    programo=request.POST["select"]
    year=request.POST["select2"]
    obj=batch()
    obj.year=year
    res=program.objects.get(id=programo)
    obj.PROGRAM= res
    obj.save()
    return redirect('/myapp/viewbatch/#about')


# def admincheckbatch(request):
#     c=request.GET["c"]
#     print(c)
#     h=batch.objects.filter(batch=c)
#     if h.exists():
#         return JsonResponse({'status':'no'})
#     else:
#         return JsonResponse({'status':'ok'})

def viewbatch(request):
    res= batch.objects.all()
    print(res)
    return render(request,"viewbatch.html",{'data':res})

def editbatch(request,did):
    res=batch.objects.get(id=did)
    res2=program.objects.all()
    return render(request,"editbatch.html",{'data':res,'data2':res2,"d":range(2019,2030,1)})

def editbatch_post(request):
    did=request.POST["h1"]
    pgm=request.POST["select"]
    year=request.POST["select2"]
    res=batch.objects.filter(pk=did).update(PROGRAM=pgm,year=year)
    return redirect('/myapp/viewbatch/#about')

def deletebatch(request,id):
    res = batch.objects.get(pk=id).delete()
    # return redirect('/myapp/viewbatch/#about')
    return HttpResponse('''<script>alert("Are you sure!");window.location="/myapp/viewbatch/#about"</script>''')



def assignstaff(request):
    res=department.objects.all()
    res2=batch.objects.all()
    res3=faculty.objects.all()
    return render(request,"assignstaff.html",{'data':res,'data2':res2,'data3':res3})

def assignstaff_post(request):
    dept=request.POST["select"]
    bat=request.POST["select1"]
    sta=request.POST["select2"]
    desig=request.POST["select3"]

    try:
        staff_obj = staff.objects.get(DEPARTMENT__id = dept,post = desig)
        print(staff_obj.FACULTY.fname,"***********************************")
    except:
        staff_obj = None

    if staff_obj is not None:
        return HttpResponse('''<script>alert("HOD Already Exists!");window.location="/myapp/viewstaff/#about"</script>''')


    obj=staff()

    if bat!="none":

        obj.post=desig
        obj.BATCH_id=bat
        obj.DEPARTMENT_id=dept
        obj.FACULTY_id=sta
        obj.save()
        fobj=faculty.objects.get(id=sta)
        lobj=login.objects.get(id=fobj.LOGIN_id)
        lobj.type='tutor'
        lobj.save()

    else:
        obj.post = desig
        obj.DEPARTMENT_id = dept
        obj.FACULTY_id = sta
        obj.save()
        fobj = faculty.objects.get(id=sta)
        lobj = login.objects.get(id=fobj.LOGIN_id)
        lobj.type = 'hod'
        lobj.save()

    return redirect('/myapp/viewstaff/#about')

def viewstaff(request):
    res = staff.objects.order_by('FACULTY__fname')
    return render(request,"viewstaff.html",{'data':res})


def changevalue(request,id):
    oo=faculty.objects.filter(DEPARTMENT_id=id)
    x=[]
    for i in oo:
        x.append({"id":i.id,"fname":i.fname,"lname":i.lname})
    print(x,"haiiiii")
    return JsonResponse({"data":x,"status":"ok"})



def get_staffs(request):
    if request.GET:
        dept_id = request.GET.get('department_id', None)
        dept = get_object_or_404(department, pk=dept_id)
        staffs = faculty.objects.filter(DEPARTMENT=dept)
        data = [{'id': staff.id, 'name': staff.fname+" "+staff.lname} for staff in staffs]
        print(data,"````````````````````````````````````````````````````")
        return JsonResponse(data, safe=False)



def editstaff(request,did):
    res=staff.objects.get(id=did)
    res2=department.objects.all()
    res3=batch.objects.all()
    res4=faculty.objects.all()
    return render(request,"editstaff.html",{'data':res,'data2':res2,'data3':res3,'data4':res4})

def editstaff_post(request):
    did=request.POST["h1"]
    dept = request.POST["select"]
    bat = request.POST["select1"]
    sta = request.POST["select2"]
    desig = request.POST["select3"]

    res=staff.objects.filter(pk=did).update(DEPARTMENT=dept,BATCH=bat,FACULTY=sta,post=desig)
    return redirect('/myapp/viewstaff/#about')


def deletestaff(request,id):
    res = staff.objects.get(pk=id).delete()
    # return redirect('/myapp/viewstaff/#about')
    return HttpResponse('''<script>alert("Are you sure!");window.location="/myapp/viewstaff/#about"</script>''')


def notifi(request):
    return render(request,"notification.html")

def notification_post(request):
    notific = request.POST['textfeild']
    notobj = notification()
    notobj.notification = notific
    notobj.date = datetime.datetime.now()
    notobj.save()
    return redirect('/myapp/viewnotification/#about')

def viewnotification(request):
    res=notification.objects.all()
    return render(request,"viewnotification.html",{'data':res})

def viewnotification_post(request):
    fdate = request.POST['d1']
    tdate = request.POST['d2']
    res = notification.objects.filter(date__range=[fdate,tdate])
    return render(request, "viewnotification.html", {'data':res})

def deletenotification(request,id):
    res=notification.objects.get(pk=id).delete()
    return HttpResponse('''<script>alert("Are you sure!");window.location="/myapp/viewnotification/#about"</script>''')


def chngpwd(request):
    return render(request,"chngpwd.html")

def chngpwd_post(request):
    current = request.POST["textfield"]
    new=request.POST["textfield2"]
    confirm=request.POST["textfield3"]
    return HttpResponse("ok")



def viewstudents(request):
    return render(request,"viewstudents.html")






def calendarm(request):
    return render(request,"calendar.html")




def addevent(request,date,event,day):
    print(date)

    obj=calendar()
    obj.date=day
    obj.event=event
    obj.save()


    return HttpResponse("ok")



def view_allevent(request,day):
    # print(date)
    obj=calendar.objects.filter(date=day)
    l=[]
    for i in obj:
       # l.append({"date":i.date,"events":i.event})
       l.append({"events":[{'occasion':i.event,'invited_count':0,'year':i.date.year,'month':i.date.month,'day':i.date.day,'cancelled':'false'}]})
    print(l)
    return JsonResponse({'status':'ok','data':l})



def calendars(request):
    return render(request, "cal.html")











#-----------------------------------------------------ANDROID----------------------------------------------#


def and_login(request):
    username = request.POST['username']
    password = request.POST['password']
    import hashlib
    passwords = password
    result = hashlib.sha256(passwords.encode())
    if login.objects.filter(username=username, password=result.hexdigest()).exists():
        d = login.objects.get(username=username, password=result.hexdigest())
        if d.type == "hod":
            res3=faculty.objects.get(LOGIN_id=d.id)
            return JsonResponse({'status': 'ok', 'lid': d.id, 'type': d.type,'did':res3.DEPARTMENT_id,'sid':res3.id})
        elif d.type=="tutor":
            res3 = faculty.objects.get(LOGIN_id=d.id)
            return JsonResponse({'status': 'ok', 'lid': d.id, 'type': d.type, 'did': res3.DEPARTMENT_id,'sid':res3.id})
        elif d.type=="teacher":
            res3 = faculty.objects.get(LOGIN_id=d.id)
            return JsonResponse({'status': 'ok', 'lid': d.id, 'type': d.type, 'did': res3.DEPARTMENT_id, 'sid':res3.id})

        else:
            res3 = student.objects.get(LOGIN_id=d.id)
            return JsonResponse({'status': 'ok', 'lid': d.id, 'type': d.type,'did':res3.id })



    else:
        return JsonResponse({'status':'no'})

def view_profile(request):
    lid=request.POST['lid']
    res=faculty.objects.get(LOGIN_id=lid)
    return JsonResponse({'status':'ok','fname':res.fname,'lname':res.lname,'dob':res.dob,'house':res.house,'place':res.place,'post':res.place,'pin':res.pin,'district':res.district,'email':res.email,'phno':res.phno,'certificate':res.certificate,'photo':res.photo})

def student_viewprofile(request):
    lid=request.POST['lid']
    res=student.objects.get(LOGIN=lid)
    return JsonResponse({'status':'ok','fname':res.fname,'lname':res.lname,'dob':res.dob,'house':res.house,'place':res.place,'post':res.place,'pin':res.pin,'district':res.district,'email':res.email,'phno':res.phno,'photo':res.photo})


def changepassword(request):
    lid = request.POST['lid']
    current=request.POST['op']
    new=request.POST['np']
    import hashlib
    passwords = new
    result = hashlib.sha256(passwords.encode())
    cp = hashlib.sha256(current.encode())
    lobj=login.objects.filter(id=lid,password=cp.hexdigest())
    if lobj.exists():
        lobjj=login.objects.get(id=lid,password=cp.hexdigest())
        lobjj.password=result.hexdigest()
        lobjj.save()
        return JsonResponse({'status': 'ok'})
    else:
        return JsonResponse({'status': 'no'})


def addsubject(request):
    subjectname =request.POST['subject']
    sem = request.POST['sem']
    pgm =request.POST['pgm']

    sobjj=subject.objects.filter(name__contains=subjectname,semester=sem,PROGRAM_id=pgm)
    if sobjj.exists():
        return JsonResponse({'status': 'no'})
    else:
        sobj=subject()
        sobj.name=subjectname
        sobj.PROGRAM_id=pgm
        sobj.semester=sem
        sobj.save()
        return JsonResponse({'status': 'ok'})

def and_view_programs(request):
    lid =request.POST['lid']
    l=[]
    sobj = faculty.objects.get(LOGIN_id=lid)
    pobj= program.objects.filter(DEPARTMENT= sobj.DEPARTMENT)
    for i in pobj:
        l.append({'id':i.id,'name':i.pname})
    return JsonResponse({'status': 'ok','data':l})

def and_view_subjects(request):
    lid=request.POST['lid']
    fobj=faculty.objects.get(LOGIN_id=lid)
    res=subject.objects.filter(PROGRAM__DEPARTMENT=fobj.DEPARTMENT)
    print(res,"-------------------------")
    l=[]
    for i in res:
        l.append({'id':i.id,'name':i.name,'sem':i.semester,'pgm':i.PROGRAM.pname,'pid':i.PROGRAM_id})

    print(l)
    return JsonResponse({'status': 'ok','data':l})



def and_view_subjects_sem1(request):
    sem=1
    print(sem)
    sub=subject.objects.filter(semester=sem)
    print(sub,"------------------------------------------------------")

    l = []
    for i in sub:
        l.append({'id': i.id, 'name': i.name, 'sem': i.semester, 'pgm': i.PROGRAM.pname, 'pid': i.PROGRAM_id})

    print(l)
    return JsonResponse({'status': 'ok', 'data': l})




def hod_delete_subject(request):
    suid=request.POST['suid']
    delobj=subject.objects.filter(id=suid).delete()
    return JsonResponse({'status': 'ok'})


def and_view_subjects_sem(request):
    sem=request.POST['sem']
    print(sem)
    sub=subject.objects.filter(semester=sem)
    print(sub,"------------------------------------------------------")

    l = []
    for i in sub:
        l.append({'id': i.id, 'name': i.name, 'sem': i.semester, 'pgm': i.PROGRAM.pname, 'pid': i.PROGRAM_id})

    print(l)
    return JsonResponse({'status': 'ok', 'data': l})


def and_view_faculty(request):
    lid = request.POST['lid']
    fobj = faculty.objects.get(LOGIN_id=lid)
    res=faculty.objects.filter(DEPARTMENT=fobj.DEPARTMENT)

    l = []
    for i in res:
        l.append({'fid': i.id, 'fname': i.fname, 'lname': i.lname})

    return JsonResponse({'status': 'ok', 'data': l})



def addsubjectallocation(request):
    subjectname = request.POST['subject']
    faculty = request.POST['faculty']

    sobjj=subjectallocation.objects.filter(SUBJECT_id=subjectname)
    if sobjj.exists():
        return JsonResponse({'status': 'no'})
    else:

        sobj=subjectallocation()
        sobj.SUBJECT_id=subjectname
        sobj.FACULTY_id=faculty
        sobj.save()
        return JsonResponse({'status': 'ok'})



def and_view_subjectallocation(request):
    l=[]
    lid = request.POST['lid']
    fobj = faculty.objects.get(LOGIN_id=lid)
    sobj=subjectallocation.objects.filter(FACULTY__DEPARTMENT=fobj.DEPARTMENT)
    for i in sobj:
        l.append({'sid':i.SUBJECT_id,'facultyid':i.FACULTY_id,'subject':i.SUBJECT.name,'fname':i.FACULTY.fname,'lname':i.FACULTY.lname,'sem':i.SUBJECT.semester})

    return JsonResponse({'status': 'ok','data':l})


def and_view_subjectallocation_sem(request):
    l=[]
    lid = request.POST['lid']
    sem = request.POST['sem']
    fobj = faculty.objects.get(LOGIN_id=lid)
    sobj=subjectallocation.objects.filter(FACULTY__DEPARTMENT=fobj.DEPARTMENT,SUBJECT__semester=sem)
    for i in sobj:
        l.append({'sid':i.SUBJECT_id,'facultyid':i.FACULTY_id,'subject':i.SUBJECT.name,'fname':i.FACULTY.fname,'lname':i.FACULTY.lname,'sem':i.SUBJECT.semester})

    return JsonResponse({'status': 'ok','data':l})





def tutor_viewclass(request):
    lid=request.POST['lid']
    sobj=staff.objects.filter(FACULTY=faculty.objects.get(LOGIN_id=lid))
    l=[]
    for i in sobj:
        l.append({'id':i.id,'post':i.post,'batch':i.BATCH.year,'department':i.DEPARTMENT.department})
    return JsonResponse({'status': 'ok','data':l})


def teacher_view_subjects(request):
    lid=request.POST['lid']

    sobj=subjectallocation.objects.filter(FACULTY=faculty.objects.get(LOGIN_id=lid))
    print(sobj,"+++++++++++++++++++++")
    l = []
    for i in sobj:
        l.append({'id': i.id, 'FACULTY_id': i.FACULTY_id,'SUBJECT_id': i.SUBJECT_id,'name':i.SUBJECT.name,'semester':i.SUBJECT.semester})
    return JsonResponse({'status': 'ok', 'data': l})

def teacher_view_subjects_sem_wise(request):
    lid=request.POST['lid']
    sem=request.POST['sem']

    sobj=subjectallocation.objects.filter(FACULTY=faculty.objects.get(LOGIN_id=lid),SUBJECT__semester=sem)
    print(sobj,"+++++++++++++++++++++")
    l = []
    for i in sobj:
        l.append({'id': i.id, 'FACULTY_id': i.FACULTY_id,'SUBJECT_id': i.SUBJECT_id,'name':i.SUBJECT.name,'semester':i.SUBJECT.semester})
    return JsonResponse({'status': 'ok', 'data': l})



def tutor_add_student(request):
    lid=request.POST['lid']
    ktu_id=request.POST['ktu_id']
    fname=request.POST['fname']
    lname=request.POST['lname']
    photo=request.POST['photo']
    house=request.POST['house']
    place=request.POST['place']
    post=request.POST['post']
    pin=request.POST['pin']
    district=request.POST['district']
    email=request.POST['email']
    phno=request.POST['phone']
    dob=request.POST['dob']
    semester_id=request.POST['semester']
    sobj = staff.objects.get(FACULTY_id=faculty.objects.get(LOGIN_id=lid))
    dept_id = sobj.DEPARTMENT_id
    batch_id = sobj.BATCH_id
    program_id=sobj.BATCH.PROGRAM_id
    import hashlib
    result = hashlib.sha256(ktu_id.encode())

    lobj=login()
    lobj.username=email
    lobj.password=result.hexdigest()
    lobj.type='student'
    lobj.save()
    sobj=student()
    sobj.ktu_id=ktu_id
    sobj.fname=fname
    sobj.lname=lname
    sobj.house=house
    sobj.place=place
    sobj.post=post
    sobj.pin=pin
    sobj.district=district
    sobj.email=email
    sobj.phno=phno
    sobj.dob=dob
    sobj.BATCH_id=batch_id
    sobj.DEPARTMENT_id=dept_id
    sobj.SEMESTER_id=semester_id
    sobj.PROGRAM_id=program_id
    import time, datetime
    from encodings.base64_codec import base64_decode
    import base64

    timestr = time.strftime("%Y%m%d-%H%M%S")
    print(timestr)
    a = base64.b64decode(photo)
    fh = open("C:\\Users\\Hp\\PycharmProjects\\Edulink\\media\\" + timestr + ".jpg", "wb")
    path = "/media/" + timestr + ".jpg"
    fh.write(a)
    fh.close()
    sobj.photo=path
    sobj.LOGIN=lobj
    sobj.save()
    return JsonResponse({'status': 'ok'})




def tutor_viewstudents(request):
    lid=request.POST['lid']
    bobj = staff.objects.get(FACULTY=faculty.objects.get(LOGIN_id=lid))
    sobj=student.objects.filter(BATCH=bobj.BATCH).order_by('fname')
    l=[]
    for i in sobj:
        l.append({'id':i.id,'ktuid':i.ktu_id,'fname':i.fname,'lname':i.lname,'dob':i.dob,
                  'house':i.house,'place':i.place,'post':i.post,'pin':i.pin,'district':i.district,'email':i.email,'phno':i.phno,'photo':i.photo,'batch':i.BATCH.year,'dept':i.DEPARTMENT.department,'sem':i.semester})
    return JsonResponse({'status': 'ok','data':l})


def tutor_update_student(request):
    sid=request.POST['sid']
    sobj=student.objects.get(id=sid)
    print(sobj,"hlooooooooooooooo")
    return JsonResponse({'status': 'ok','ktuid':sobj.ktu_id,'fname':sobj.fname,'lname':sobj.lname,'dob':sobj.dob,'house':sobj.house,'place':sobj.place,'post':sobj.post,'pin':sobj.pin,'district':sobj.district,'email':sobj.email,'phno':sobj.phno,'photo':sobj.photo,'batch':sobj.BATCH.year,'dept':sobj.DEPARTMENT.department,'sem':sobj.semester})

def tutor_update_student_post(request):
    sid=request.POST['sid']
    print(sid)
    ktuid = request.POST['ktu_id']
    fname = request.POST['fname']
    lname = request.POST['lname']
    photo = request.POST['photo']
    house = request.POST['house']
    place = request.POST['place']
    post = request.POST['post']
    pin = request.POST['pin']
    district = request.POST['district']
    email = request.POST['email']
    phno = request.POST['phone']
    dob = request.POST['dob']
    semester_id = request.POST['semester']

    sobj = student.objects.get(id=sid)

    if photo!="none":
        sobj.fname = fname
        sobj.lname = lname
        sobj.house = house
        sobj.place = place
        sobj.post = post
        sobj.pin = pin
        sobj.district = district
        sobj.email = email
        sobj.phno = phno
        sobj.dob = dob
        sobj.SEMESTER_id = semester_id
        import time, datetime
        from encodings.base64_codec import base64_decode
        import base64

        timestr = time.strftime("%Y%m%d-%H%M%S")
        print(timestr)
        a = base64.b64decode(photo)
        fh = open("C:\\Users\\Hp\\PycharmProjects\\Edulink\\media\\" + timestr + ".jpg", "wb")
        path = "/media/" + timestr + ".jpg"
        fh.write(a)
        fh.close()
        sobj.photo = path
        sobj.save()
        return JsonResponse({'status': 'ok'})
    else:
        sobj.fname = fname
        sobj.lname = lname
        sobj.house = house
        sobj.place = place
        sobj.post = post
        sobj.pin = pin
        sobj.district = district
        sobj.email = email
        sobj.phno = phno
        sobj.dob = dob
        sobj.SEMESTER_id = semester_id
        sobj.save()
        return JsonResponse({'status': 'ok'})



def teacher_view_all_student(request):
    sobj=student.objects.order_by('fname')
    l=[]
    for i in sobj:
        l.append({'id': i.id, 'ktuid': i.ktu_id, 'fname': i.fname, 'lname': i.lname, 'house': i.house, 'place': i.place,
                  'post': i.post, 'pin': i.pin, 'district': i.district, 'email': i.email, 'phno': i.phno,
                  'photo': i.photo, 'batch': i.BATCH.year, 'dept': i.DEPARTMENT.department, 'sem': i.semester,'dob':i.dob})

    return JsonResponse({'status': 'ok', 'data': l})


def teacher_view_all_student_post(request):
    sem = request.POST['sem']
    dep = request.POST['did']
    print(sem)

    sobj = student.objects.filter(DEPARTMENT_id=dep, semester=sem).order_by('fname')
    l = []
    for i in sobj:
        l.append({'id': i.id, 'ktuid': i.ktu_id, 'fname': i.fname, 'lname': i.lname, 'house': i.house, 'place': i.place,
                  'post': i.post, 'pin': i.pin, 'district': i.district, 'email': i.email, 'phno': i.phno,
                  'photo': i.photo, 'batch': i.BATCH.year, 'dept': i.DEPARTMENT.department, 'sem': i.semester,
                  'dob': i.dob})

    return JsonResponse({'status': 'ok', 'data': l})









def show_courses(request):
    # cid=request.POST['id']

    cobj=program.objects.all()
    l=[]
    for i in cobj:
        l.append({'pname':i.pname,"id":i.id})
    return JsonResponse({'status': 'ok','data':l})


def show_sem(request):
    return JsonResponse({'status': 'ok'})


def show_subject(request):
    cid=request.POST['cid']
    sem=request.POST['sem']

    print(cid)
    print(sem)
    sobj=subject.objects.filter(PROGRAM_id=cid,semester=sem)
    l=[]
    for i in sobj:
        l.append({'sub_name':i.name,"id":i.id})
    return JsonResponse({'status': 'ok','data':l})


def add_timetable(request):

    day=request.POST['day']
    hour=request.POST['hour']
    sub = request.POST['name']
    sem = request.POST['sem']
    cid = request.POST['cid']

    print(day,"-----------")
    print(hour)
    print(sub)
    print(sem)

    tobj=timetable.objects.filter(day=day,hour=hour,SUBJECT_id=sub)
    if tobj.exists():
        return JsonResponse({'status': 'no'})
    else:
        tobj=timetable()
        tobj.hour=hour
        tobj.day=day
        tobj.SUBJECT_id=sub
        tobj.PROGRAM_id=cid
        tobj.save()

        return JsonResponse({'status': 'ok'})





def show_program(request):
    did=request.POST['did']
    # cobj=program.objects.filter(DEPARTMENT=did)
    cobj=program.objects.all()
    l=[]

    for i in range(len(cobj)):
        print(cobj[0])
        if i==0:
            l.append({'pname':"Choose program", "id": ""})

        l.append({'pname':cobj[i].pname,"id":cobj[i].id})
    return JsonResponse({'status': 'ok','data':l})



def hod_view_timetable(request):
    print(request.POST)
    PROGRAM = request.POST['program']
    SEMESTER = request.POST['select2']

    # print(PROGRAM,"hlooooooooooooooo")
    # print(SEMESTER,"haiiiiiiiiiiiiiiiiiiiii")



    hour = ["1", "2", "3", "4", "5"]
    day=["Monday","Tuesday","Wednesday","Thursday","Friday"]

    ttab=[]
    for i in day:
        ds=[]
        for j in hour:

            # print(str(i),"hloooooo")
            # print(str(j),"hiiiiiiiii")
            res3=timetable.objects.raw("SELECT * FROM `edulinkapp_timetable` INNER JOIN `edulinkapp_subject` ON `edulinkapp_timetable`.`SUBJECT_id`=`edulinkapp_subject`.`id` WHERE `day`='"+str(i)+"' AND `hour`='"+str(j)+"' AND `edulinkapp_timetable`.`PROGRAM_id`='"+PROGRAM+"' AND `edulinkapp_subject`.`semester`='"+SEMESTER+"'")
            # print(res3,"helllo",len(res3))

            if len(res3)>0:
                # print(res3)


                ds.append({'s' :  res3[0].SUBJECT.name})
            else:
                ds.append({'s':"free"})
        ttab.append(ds)
    # print(ttab)
    return JsonResponse({'status': 'ok','timetabl':ttab})


def teacher_view_timetable(request):
    print(request.POST)
    lid = request.POST['lid']
    PROGRAM = request.POST['program']
    SEMESTER = request.POST['select2']
    fid=str(faculty.objects.get(LOGIN_id=lid).id)

    # print(PROGRAM,"hlooooooooooooooo")
    # print(SEMESTER,"haiiiiiiiiiiiiiiiiiiiii")
    print()



    hour = ["1", "2", "3", "4", "5"]
    day=["Monday","Tuesday","Wednesday","Thursday","Friday"]

    ttab=[]
    for i in day:
        ds=[]
        for j in hour:

            # print(str(i),"hloooooo")
            # print(str(j),"hiiiiiiiii")
            # res3=timetable.objects.raw("SELECT * FROM `edulinkapp_timetable` INNER JOIN `edulinkapp_subject` ON `edulinkapp_timetable`.`SUBJECT_id`=`edulinkapp_subject`.`id`   WHERE `day`='"+str(i)+"' AND `hour`='"+str(j)+"' AND `edulinkapp_timetable`.`PROGRAM_id`='"+PROGRAM+"' AND `edulinkapp_subject`.`semester`='"+SEMESTER+"'")
            # res3=timetable.objects.raw("SELECT * FROM `edulinkapp_timetable` INNER JOIN `edulinkapp_subject` ON `edulinkapp_timetable`.`SUBJECT_id`=`edulinkapp_subject`.`id` RIGHT JOIN `edulinkapp_subjectallocation` ON `edulinkapp_subjectallocation`.`SUBJECT_id`=`edulinkapp_subject`.`id`  WHERE `day`='"+str(i)+"' AND `hour`='"+str(j)+"' AND `edulinkapp_timetable`.`PROGRAM_id`='"+PROGRAM+"' AND `edulinkapp_subject`.`semester`='"+SEMESTER+"' and `edulinkapp_subjectallocation`.`FACULTY_id`='"+fid+"'")
            res3=timetable.objects.raw("SELECT * FROM `edulinkapp_timetable` INNER JOIN `edulinkapp_subject` ON `edulinkapp_timetable`.`SUBJECT_id`=`edulinkapp_subject`.`id`  JOIN `edulinkapp_subjectallocation` ON `edulinkapp_subjectallocation`.`SUBJECT_id`=`edulinkapp_subject`.`id`  WHERE `day`='"+str(i)+"' AND `hour`='"+str(j)+"' AND `edulinkapp_timetable`.`PROGRAM_id`='"+PROGRAM+"' AND `edulinkapp_subject`.`semester`='"+SEMESTER+"' AND `FACULTY_id`='"+fid+"' ")
            # res3=timetable.objects.raw("SELECT * FROM `edulinkapp_timetable` "\
# "INNER JOIN `edulinkapp_subject` ON `edulinkapp_timetable`.`SUBJECT_id`=`edulinkapp_subject`.`id`  "\
# "left JOIN `edulinkapp_subjectallocation` ON `edulinkapp_subjectallocation`.`SUBJECT_id`=`edulinkapp_subject`.`id`  "\
# "WHERE `day`='Monday' AND `hour`='4'"\
# " AND `edulinkapp_timetable`.`PROGRAM_id`='13' "\
# " AND `edulinkapp_subject`.`semester`='1' AND `FACULTY_id`='3' ")
            # print(res3,"helllo",len(res3))

            if len(res3)>0:
                # print(res3)


                ds.append({'s' :  res3[0].SUBJECT.name})
            else:
                ds.append({'s':"free"})
        ttab.append(ds)
    print(ttab)
    return JsonResponse({'status': 'ok','timetabl':ttab})




def search_subject(request):

    lid=request.POST['lid']
    fobj = faculty.objects.get(LOGIN_id=lid)

    pgm=request.POST['pname']
    sem=request.POST['sem']

    pobj=program.objects.get(DEPARTMENT=fobj.DEPARTMENT,pname=pgm,sem=sem)



    return JsonResponse({'status': 'ok'})



def show_dept(request):


    did = request.POST['did']
    cobj = department.objects.all()
    l = []
    for i in cobj:
        l.append({'department': i.department, "id": i.id})

    return JsonResponse({'status': 'ok', 'data': l})



def teacher_attendance(request):
    pid=request.POST['pid']
    semester=request.POST['semester']
    sobj=student.objects.filter(semester=semester,PROGRAM_id=pid).order_by('fname')
    l = []
    for i in sobj:
        l.append({'id': i.id,'fname': i.fname,'lname':i.lname})

    return JsonResponse({'status': 'ok','data': l})

def teacher_add_attendance(request):
    selected=request.POST['selected']
    print(selected,"slctd")
    hour = request.POST['hour']
    pid = request.POST['pid']
    choices = selected
    s = choices.split(",")
    print("ch",s)
    from datetime import datetime
    # datef=datetime.now().strftime("%Y-%m-%d")
    dayname=datetime.today().strftime('%A')

    tobj=timetable.objects.filter(hour=hour,day=dayname,PROGRAM_id=pid)
    print("timetble",tobj)
    # sobj=substitution.objects.filter(date=datef,hour=hour)
    tobjj=''
    print(tobj,"hloooo")
    if tobj.exists():
        tobjj=timetable.objects.get(hour=hour,day=dayname,PROGRAM_id=pid)
        print("ttttt",tobjj)
    for i in s:
        if str(i) != "":
            aob=attendance()
            aob.STUDENT_id = str(i)
            aob.hour=hour
            aob.date=datetime.now().strftime("%d-%m-%Y")
            aob.TIMETABLE_id=tobjj
            aob.save()
    return  JsonResponse({'status':'ok'})

def teacher_view_attendance(request):
    pid = request.POST['pid']
    semesters = request.POST['semester']
    date = request.POST['date']
    hour=request.POST['hour']

    print(pid)
    print(semesters)
    print(date)
    print(hour)

    print(request.POST)

    all_students=student.objects.filter(semester=semesters,PROGRAM_id=pid).order_by('fname')
    # sobj=attendance.objects.all()
    sobj = attendance.objects.filter(STUDENT__PROGRAM_id=pid,STUDENT__semester=semesters,date__contains=date,hour__contains=hour)
    print(sobj,".....")

    l = []
    for i in all_students:
        sobj = attendance.objects.filter(STUDENT__PROGRAM_id=pid, STUDENT__semester=semesters, date__contains=date,
                                         hour__contains=hour,STUDENT_id=i.id)
        if sobj.exists():
            l.append({'id': i.id, 'fname': i.fname, 'lname': i.lname,"photo":i.photo,'uid':i.id,"status":"present"})
        else:
            l.append({'id': i.id, 'fname': i.fname,"photo":i.photo, 'lname': i.lname, 'uid': i.id,
                      "status": "absent"})

    print(l)
    return JsonResponse({'status': 'ok','data':l})







def students_view_attendance(request):

    sid=request.POST['sid']
    sem=request.POST['semester']

    # sid="1"
    sobj=student.objects.get(id=sid)

    pgm=sobj.PROGRAM_id
    rest=[]
    aobj=subject.objects.filter(PROGRAM_id=pgm,semester=sem)
    for i in aobj:
        scount=0
        qq=attendance.objects.raw("SELECT edulinkapp_attendance.* FROM `edulinkapp_attendance`, `edulinkapp_timetable` WHERE `edulinkapp_timetable`.`id`=`edulinkapp_attendance`.`TIMETABLE_id` AND `STUDENT_id`='"+sid+"' AND `edulinkapp_timetable`.`SUBJECT_id`='"+str(i.id)+"' ")
        print(qq)
        for j in qq:
            print(j)
            scount+=1

        rest.append({"subject":i.name,"count":scount,"perc":"0"})




    return JsonResponse({'status': 'ok',"data":rest})




def teacher_view_attendance_percentage(request):
    pid = request.POST['pid']
    semesters = request.POST['semester']

    sobj = student.objects.filter(semester=semesters, PROGRAM_id=pid).order_by('fname')
    l = []
    for i in sobj:
        l.append({'id': i.id, 'fname': i.fname, 'lname': i.lname})

    return JsonResponse({'status': 'ok', 'data': l})



def teacher_view_attendance_overall(request):

    print(request.POST)
    sid=request.POST['sid']
    sem=request.POST['semester']

    # sid="1"
    sobj=student.objects.get(id=sid)

    pgm=sobj.PROGRAM_id
    rest=[]
    aobj=subject.objects.filter(PROGRAM_id=pgm,semester=sem)
    for i in aobj:
        scount=0
        tot=0
        qq=attendance.objects.raw("SELECT edulinkapp_attendance.* FROM `edulinkapp_attendance`, `edulinkapp_timetable` WHERE `edulinkapp_timetable`.`id`=`edulinkapp_attendance`.`TIMETABLE_id` AND `STUDENT_id`='"+sid+"' AND `edulinkapp_timetable`.`SUBJECT_id`='"+str(i.id)+"' ")
        qq1=attendance.objects.raw("SELECT DISTINCT `edulinkapp_attendance`.id,`edulinkapp_attendance`.`date`,`edulinkapp_attendance`.`hour` FROM `edulinkapp_attendance` JOIN `edulinkapp_timetable` ON `edulinkapp_timetable`.`id`=`edulinkapp_attendance`.`TIMETABLE_id` WHERE `STUDENT_id`='"+sid+"' AND `edulinkapp_timetable`.`SUBJECT_id`='"+str(i.id)+"'")


        for k in qq:
            scount+=1
        for j in qq1:
            tot+=1

        try:
            rest.append({"subject":i.name,"count":scount,"perc": scount/tot})
        except:
            rest.append({"subject":i.name,"count":scount,"perc": '0'})

        for i in rest:
            # l.append({"subject":i.name,"count":scount,"perc":"0"})
            print(rest,";;;;;;;")

    return JsonResponse({'status': 'ok',"data":rest})



def teacher_add_substitution(request):

    pid = request.POST['pid']
    sid=request.POST["sid"]
    semesters = request.POST['semester']
    date = request.POST["date"]
    date=date.split("-")
    dates_new=date[2]+"-"+date[1]+"-"+date[0]

    import datetime
    import calendar

    def findDay(date):
        born = datetime.datetime.strptime(date, '%d %m %Y').weekday()
        return (calendar.day_name[born])

    # Driver program
    date =date[0]+" "+date[1]+" "+date[2]
    print(findDay(date))
    ds=str(findDay(date))
    hours=[]
    print(sid)
    slobj = subjectallocation.objects.filter(FACULTY_id=sid)
    print(slobj,"slobj")
    if slobj.exists():
        for i in slobj:
            sub = subject.objects.filter(PROGRAM_id=pid, semester=semesters,id=i.SUBJECT_id)
            if sub.exists():
                for k in sub:
                    time=timetable.objects.filter(day=ds,SUBJECT_id=k.id)
                    if time.exists():
                        for j in time:
                            hours.append({"hour":j.hour})

    sobj = subject.objects.filter(PROGRAM_id=pid, semester=semesters)
    print(sobj)
    l = []
    for i in sobj:
        print("***********************************************")
        try:
            slobj = subjectallocation.objects.get(SUBJECT_id=i.id)
            print(slobj.FACULTY_id)
            if str(slobj.FACULTY_id) != sid:
                l.append({'sub_name': i.name, 'id': i.id})
        except:
            pass

    print(hours)
    return JsonResponse({'status': 'ok', 'data': l,"hours":hours})



def teacher_add_substitution_post(request):

    date = request.POST['date']
    date = date.split("-")
    dates_new = date[2] + "-" + date[1] + "-" + date[0]

    hour = request.POST['hour']
    # sub = request.POST['name']
    subid=request.POST['subid']
    sid=request.POST['sid']

    sobj=substitution.objects.filter(SUBJECT_id=subid,date=date,hour=hour,FACULTY_id=sid)


    sobj=substitution()
    sobj.SUBJECT_id=subid
    sobj.FACULTY_id=sid
    sobj.date=dates_new
    sobj.hour=hour
    sobj.save()

    return JsonResponse({'status': 'ok'})

def teacher_view_substitution(request):
    lid = request.POST['lid']

    sobj = substitution.objects.filter(FACULTY=faculty.objects.get(LOGIN_id=lid))
    # print(sobj, "+++++++++++++++++++++")

    l = []
    for i in sobj:
        l.append({'pname':i.SUBJECT.PROGRAM.pname, 'SUBJECT_id': i.SUBJECT_id, 'name': i.SUBJECT.name, 'semester': i.SUBJECT.semester,'date':i.date,'hour':i.hour})
    return JsonResponse({'status': 'ok', 'data': l})



def teacher_view_substitution_search(request):
    date= request.POST['date']
    from datetime import datetime

    # Input date in "dd-mm-yyyy" format
    date_string = "18-04-2023"

    # Convert date string to datetime object
    date_object = datetime.strptime(date, "%d-%m-%Y")

    # Convert datetime object to string in "yyyy-mm-dd" format
    formatted_date_string = date_object.strftime("%Y-%m-%d")

    # Print the formatted date string
    print(formatted_date_string)
    print(date)
    # import datetime
    # date=datetime.datetime.strptime(date, '%Y %m %d')
    print(";;;;;;")
    sobj=substitution.objects.filter(date=formatted_date_string)
    print(sobj)

    l = []
    for i in sobj:
        l.append({'pname': i.SUBJECT.PROGRAM.pname, 'SUBJECT_id': i.SUBJECT_id, 'name': i.SUBJECT.name,
                  'semester': i.SUBJECT.semester, 'date': i.date, 'hour': i.hour})
    return JsonResponse({'status': 'ok', 'data': l})



def view_substitution(request):
    from datetime import datetime
    pp=datetime.now()
    st=str(pp).split(" ")

    sid=request.POST['sid']

    sobj = substitution.objects.filter(date__gte=st[0])

    l = []

    for i in sobj:

        slobj = subjectallocation.objects.filter(SUBJECT_id=i.SUBJECT_id,FACULTY_id=sid)
        if slobj.exists():
            l.append({'pname':i.SUBJECT.PROGRAM.pname,'SUBJECT_id': i.SUBJECT_id, 'name': i.SUBJECT.name,'semester': i.SUBJECT.semester,'date': i.date, 'hour': i.hour})


    return JsonResponse({'status':'ok',"data":l})



def show_students(request):
    pid = request.POST['pid']
    semester = request.POST['semester']
    print(pid, "...........")
    print(semester, "..........")

    sobj = student.objects.filter(semester=semester, PROGRAM_id=pid).order_by('fname')
    print(sobj, "=======")

    l = []
    for i in sobj:
        l.append({'id': i.id, 'fname': i.fname, 'lname': i.lname})

    return JsonResponse({'status': 'ok', 'data': l})



def teacher_add_exam(request):

    return JsonResponse({'status':'ok'})


def teacher_add_internal(request):

    sid=request.POST['sid']
    print(sid,"hiiiiiiii")
    sub_id=request.POST['sub_id']
    print(sub_id,"hlooooooo")
    mark=request.POST['mark']
    from datetime import datetime
    datet=datetime.now().strftime("%d-%m-%Y")
    # datet=datetime.now().strftime("%d-%m-%Y %H:%M:%S")
    sobjj=internal.objects.filter(STUDENT_id=sid,SUBJECT_id=sub_id)
    if sobjj.exists():
        return JsonResponse({'status': 'no'})
    else:

        sobj=internal()
        sobj.mark=mark
        sobj.STUDENT_id=sid
        sobj.SUBJECT_id=sub_id
        sobj.date=datet
        sobj.save()

        return JsonResponse({'status': 'ok'})


def show_internal_mark(request):
    # sid=request.POST['slid']
    sub_id=request.POST['subject']

    sobj=internal.objects.filter(SUBJECT_id=sub_id)
    # sobj=internal.objects.filter(STUDENT_id=sid,SUBJECT_id=sub_id)


    l = []
    for i in sobj:
        l.append({'id': i.id,'mark': i.mark,'date':i.date,'fname':i.STUDENT.fname,'lname':i.STUDENT.lname})
    print(l)
    return JsonResponse({'status': 'ok', 'data': l})


def students_view_internal(request):
    sem = request.POST['semester']
    lid = request.POST['lid']
    stuid=student.objects.get(LOGIN_id=lid)
    iobj=internal.objects.filter(STUDENT_id=stuid,SUBJECT__semester=sem)
    l=[]
    for i in iobj:
        l.append({'mark':i.mark,'subject':i.SUBJECT.name})
    print(l)
    return JsonResponse({'status': 'ok','data':l})



def student_request_leave(request):
    lid = request.POST['lid']
    from_date = request.POST['from_date']
    to_date = request.POST['to_date']
    reason = request.POST['reason']
    sobj=student.objects.get(LOGIN_id=lid)
    st=staff.objects.filter(BATCH=sobj.BATCH,DEPARTMENT=sobj.DEPARTMENT)
    if st.exists():
        stt=staff.objects.get(BATCH=sobj.BATCH,DEPARTMENT=sobj.DEPARTMENT)

    lobj=medical.objects.filter(from_date__contains=from_date,to_date__contains=to_date,STUDENT_id=lid)

    if lobj.exists():
        return JsonResponse({'status':'no'})

    else:
        import datetime
        obj = medical()
        obj.STUDENT=student.objects.get(LOGIN=lid)
        obj.date=datetime.datetime.now()
        obj.from_date=from_date
        obj.to_date=to_date
        obj.reason=reason
        obj.FACULTY=stt.FACULTY
        obj.save()
        return JsonResponse({'status':'ok'})




def student_view_leave(request):
    lid = request.POST['lid']

    stuid = student.objects.get(LOGIN_id=lid)
    lobj=medical.objects.filter(STUDENT=stuid)
    l = []
    for i in lobj:
        l.append({'id':i.id ,'from_date': i.from_date, 'to_date': i.to_date,'reason':i.reason,'status':i.status,'date':i.date})
    print(l)
    return JsonResponse({'status': 'ok', 'data': l})


def tutor_medical_leave(request):
    lid = request.POST['lid']

    facid = faculty.objects.get(LOGIN_id=lid)
    lobj = medical.objects.filter(FACULTY=facid)
    l = []
    for i in lobj:
        l.append({'id': i.id, 'fname':i.STUDENT.fname +" "+ i.STUDENT.lname, 'from_date': i.from_date, 'to_date': i.to_date, 'reason': i.reason, 'status': i.status,
                  'date': i.date,'certificate':i.certificate})
    print(l)
    return JsonResponse({'status': 'ok', 'data': l})



def student_upload_medicalrep(request):

    photo=request.POST['photo']
    leaveid=request.POST['leaveid']
    lid=request.POST['lid']


    import time, datetime
    from encodings.base64_codec import base64_decode
    import base64

    timestr = time.strftime("%Y%m%d-%H%M%S")
    print(timestr)
    a = base64.b64decode(photo)
    fh = open("C:\\Users\\Hp\\PycharmProjects\\Edulink\\media\\" + timestr + ".jpg", "wb")
    path = "/media/" + timestr + ".jpg"
    fh.write(a)
    fh.close()
    leveobj=medical.objects.filter(id=leaveid).update(certificate=path)



    return JsonResponse({'status':'ok'})


def tutor_approve_leave(request):
    leaveid = request.POST['leaveid']
    leveobj = medical.objects.filter(id=leaveid).update(status="approved")
    return JsonResponse({'status':'ok'})


def tutor_reject_leave(request):
    leaveid = request.POST['leaveid']
    leveobj = medical.objects.filter(id=leaveid).update(status="rejected")
    return JsonResponse({'status':'ok'})



def teacher_upload_study_material(request):


    photo = request.POST['photo']
    subid = request.POST['subid']
    lid = request.POST['lid']

    import time, datetime
    from encodings.base64_codec import base64_decode
    import base64

    timestr = time.strftime("%Y%m%d-%H%M%S")
    print(timestr)
    a = base64.b64decode(photo)
    fh = open("C:\\Users\\Hp\\PycharmProjects\\Edulink\\media\\" + timestr + ".pdf", "wb")
    path = "/media/" + timestr + ".pdf"
    fh.write(a)
    fh.close()
    subobj=studymaterial()
    subobj.SUBJECT_id=subid
    subobj.content=path
    subobj.save()

    return JsonResponse({'status': 'ok'})


def teacher_view_studymaterial(request):

    subid=request.POST['subid']
    sobj=studymaterial.objects.filter(SUBJECT_id=subid)
    l=[]
    for i in sobj:
        l.append({'id':i.id,'name':i.SUBJECT.name,'content':i.content})

    return JsonResponse({'status': 'ok','data':l})

def teacher_delete_studymaterial(request):
    noteid=request.POST['noteid']
    delobj=studymaterial.objects.filter(id=noteid).delete()
    return JsonResponse({'status': 'ok'})



def teacher_delete_internal(request):
    inteid=request.POST['inteid']
    deleobj=internal.objects.filter(id=inteid).delete()
    return JsonResponse({'status': 'ok'})



def teacher_add_assignment(request):
    lid=request.POST['lid']
    topic=request.POST['title']
    date=request.POST['date']
    sub=request.POST['subject']
    print(sub)
    fobj=faculty.objects.get(LOGIN_id=lid)
    res=subject.objects.get(id=sub)
    aobj=assignment()
    aobj.title=topic
    aobj.date=date
    aobj.SUBJECT=res
    aobj.content="pending"
    aobj.FACULTY=fobj
    aobj.save()
    return JsonResponse({'status': 'ok'})


def student_assignment(request):
    sem = request.POST['semester']
    lid = request.POST['lid']

    aobj=assignment.objects.filter(SUBJECT__semester=sem)

    l = []
    for i in aobj:
        asobj = assignmentfiles.objects.filter(ASSIGNMENT_id=i.id, STUDENT=student.objects.get(LOGIN_id=lid))
        if asobj.exists():
            pass
        else:
            l.append({'subject': i.SUBJECT.name, 'topic':i.title, 'date':i.date,'aid':i.id})
    print(l)
    return JsonResponse({'status': 'ok', 'data': l})




def student_upload_assignment(request):

    photo = request.POST['photo']
    assid = request.POST['assid']
    lid = request.POST['lid']
    print(assid)
    print(lid)
    import time, datetime
    from encodings.base64_codec import base64_decode
    import base64

    timestr = time.strftime("%Y%m%d-%H%M%S")
    print(timestr)
    a = base64.b64decode(photo)
    fh = open("C:\\Users\\Hp\\PycharmProjects\\Edulink\\media\\" + timestr + ".pdf", "wb")
    path = "/media/" + timestr + ".pdf"
    fh.write(a)
    fh.close()

    aobj = assignmentfiles()
    aobj.file=path
    aobj.ASSIGNMENT_id=assid
    aobj.date=datetime.datetime.now().strftime("%Y-%m-%d")
    aobj.STUDENT=student.objects.get(LOGIN_id=lid)
    aobj.save()

    return JsonResponse({'status': 'ok'})




def teacher_view_assignment(request):
    sub=request.POST['subject']
    lid = request.POST['lid']
    aobj=assignment.objects.filter(FACULTY=faculty.objects.get(LOGIN_id=lid),SUBJECT_id=sub)
    l=[]
    for i in aobj:
        l.append({'title':i.title,'date':i.date,'aid':i.id})

    return JsonResponse({'status': 'ok','data':l})




def teacher_view_assignment_uploads(request):
    assid=request.POST['assid']
    aobj=assignmentfiles.objects.filter(ASSIGNMENT_id=assid)

    l=[]
    for i in aobj:
        l.append({'file':i.file,'date':i.date,'fname':i.STUDENT.fname,'lname':i.STUDENT.lname})

    return JsonResponse({'status': 'ok','data':l})







#=======================================Chat================================================


def in_message2(request):
    fromid=request.POST['fromid']
    toid=request.POST['toid']
    msg=request.POST['msg']
    import datetime

    date=datetime.datetime.now().date()

    cobj=chat()
    cobj.type="staff"
    cobj.STAFF=faculty.objects.get(LOGIN_id=fromid)
    cobj.STUDENT_id=toid
    cobj.message=msg
    cobj.date=str(date)
    cobj.save()

    return JsonResponse({'status': 'ok'})




def view_message2(request):
    fromid = request.POST['fid']
    toid = request.POST['toid']
    lastmsgid = request.POST['lastmsgid']
    sid=faculty.objects.get(LOGIN_id=fromid)

    res3 = chat.objects.raw("SELECT * FROM `edulinkapp_chat` WHERE `STAFF_id`='"+str(sid.id)+"' AND `STUDENT_id`='"+toid+"' AND `id`>'"+lastmsgid+"'")
    print(res3,type(res3),len(res3))
    l=[]
    if len(res3) > 0:
        for i in res3:
            l.append({'id': i.id,"msg":i.message,"type":i.type,"date":i.date})
    print(l)
    return JsonResponse({'status': 'ok', 'data': l})



def in_message(request):
    fromid=request.POST['fromid']

    msg=request.POST['msg']
    import datetime

    date=datetime.datetime.now().date()
    ss=student.objects.get(LOGIN_id=fromid)
    if staff.objects.filter(DEPARTMENT_id=ss.DEPARTMENT_id,BATCH_id=ss.BATCH_id,post='tutor').exists():
        staffs=staff.objects.get(DEPARTMENT_id=ss.DEPARTMENT_id,BATCH_id=ss.BATCH_id,post='tutor')




        cobj=chat()
        cobj.type="student"
        cobj.STAFF_id=staffs.FACULTY_id
        cobj.STUDENT=student.objects.get(LOGIN_id=fromid)
        cobj.message=msg
        cobj.date=str(date)
        cobj.save()

        return JsonResponse({'status': 'ok'})
    else:
        return JsonResponse({'status': 'not'})

def view_message(request):
    fromid = request.POST['fid']
    ss = student.objects.get(LOGIN_id=fromid)
    lastmsgid = request.POST['lastmsgid']
    if staff.objects.filter(DEPARTMENT_id=ss.DEPARTMENT_id,BATCH_id=ss.BATCH_id,post='tutor').exists():
        staffs=staff.objects.get(DEPARTMENT_id=ss.DEPARTMENT_id,BATCH_id=ss.BATCH_id,post='tutor')





        res3 = chat.objects.raw("SELECT * FROM `edulinkapp_chat` WHERE `STAFF_id`='" + str(
            staffs.FACULTY_id) + "' AND `STUDENT_id`='" + str(ss.id) + "' AND `id`>'" + lastmsgid + "'")
        print(res3, type(res3), len(res3))
        l = []
        if len(res3) > 0:
            for i in res3:
                l.append({'id': i.id, "msg": i.message, "type": i.type, "date": i.date})
        print(l)
        return JsonResponse({'status': 'ok', 'data': l,"sname":staffs.FACULTY.fname})
    else:
        return JsonResponse({'status': 'not'})





def andnotification(request):
    lid=request.POST['lid']
    date=request.POST['date']
    notifi=request.POST['notification']
    nobj=notification.objects.all()
    l=[]
    for i in nobj:
        l.append({'id':i.id,'date':i.date,'notification':i.notification})


    return JsonResponse({'status': 'ok','data':l})


#===================Calender=================================

def calender_get(request):
    return render(request,"S4A_calendar.html")


# (B2) ENDPOINT - GET EVENTS
def get(request,month,year):
    data = dict(request.POST)
    events = evt.get(int(data["month"]), int(data["year"]))
    return "{}" if events is None else events

# (B3) ENDPOINT - SAVE EVENT
def save(request):
    data = dict(request.POST)
    ok = evt.save(data["s"], data["e"], data["t"], data["c"], data["b"], data["id"] if "id" in data else None)
    msg = "OK" if ok else sys.last_value
    return HttpResponse(msg, 200)

# (B4) ENDPOINT - DELETE EVENT
def delete(request):
    data = dict(request.POST)
    ok = evt.delete(data["id"])
    msg = "OK" if ok else sys.last_value
    return HttpResponse(msg, 200)



#===================================================================================



