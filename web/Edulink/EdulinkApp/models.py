from django.db import models

# Create your models here.

class login(models.Model):
    username = models.CharField(max_length=50)
    password = models.CharField(max_length=100)
    type = models.CharField(max_length=50)

class department(models.Model):
    department= models.CharField(max_length=50)

class semester(models.Model):
    semester= models.CharField(max_length=50)

class program(models.Model):
    DEPARTMENT =models.ForeignKey(department,on_delete=models.CASCADE,default=0)
    pname= models.CharField(max_length=50)
    sem = models.CharField(max_length=50,default="")

class batch(models.Model):
    PROGRAM =models.ForeignKey(program,on_delete=models.CASCADE,default=1)
    year= models.CharField(max_length=50)


class faculty(models.Model):
    LOGIN =models.ForeignKey(login,on_delete=models.CASCADE,default=1)
    DEPARTMENT =models.ForeignKey(department,on_delete=models.CASCADE,default=1)
    ktu_id=models.CharField(max_length=50)
    fname=models.CharField(max_length=50)
    lname=models.CharField(max_length=50)
    photo=models.CharField(max_length=50)
    house=models.CharField(max_length=50)
    place=models.CharField(max_length=50)
    post=models.CharField(max_length=50)
    pin=models.CharField(max_length=50)
    district=models.CharField(max_length=50)
    email=models.CharField(max_length=50)
    phno=models.CharField(max_length=50)
    dob=models.CharField(max_length=50)
    certificate=models.CharField(max_length=300)

class student(models.Model):
    LOGIN = models.ForeignKey(login, on_delete=models.CASCADE, default=1)
    DEPARTMENT = models.ForeignKey(department, on_delete=models.CASCADE, default=1)
    BATCH = models.ForeignKey(batch, on_delete=models.CASCADE, default=1)
    PROGRAM=models.ForeignKey(program, on_delete=models.CASCADE, default=1)
    semester = models.IntegerField(default=1)
    ktu_id = models.CharField(max_length=50)
    fname = models.CharField(max_length=50)
    lname = models.CharField(max_length=50)
    photo = models.CharField(max_length=50)
    house = models.CharField(max_length=50)
    place = models.CharField(max_length=50)
    post = models.CharField(max_length=50)
    pin = models.CharField(max_length=50)
    district = models.CharField(max_length=50)
    email = models.CharField(max_length=50)
    phno = models.CharField(max_length=50)
    dob = models.CharField(max_length=50)

class subject(models.Model):
    PROGRAM = models.ForeignKey(program, on_delete=models.CASCADE, default=1)
    semester = models.IntegerField(default=1)
    name=models.CharField(max_length=50,default="")


class subjectallocation(models.Model):
    SUBJECT = models.ForeignKey(subject, on_delete=models.CASCADE, default=1)
    FACULTY = models.ForeignKey(faculty, on_delete=models.CASCADE, default=1)



class studymaterial(models.Model):
    SUBJECT = models.ForeignKey(subject, on_delete=models.CASCADE, default=1)
    content=models.CharField(max_length=50)

class assignment(models.Model):
    SUBJECT = models.ForeignKey(subject, on_delete=models.CASCADE, default=1)
    FACULTY = models.ForeignKey(faculty, on_delete=models.CASCADE, default=1)
    title=models.CharField(max_length=50)
    date=models.CharField(max_length=50)
    content = models.CharField(max_length=50)
    mark = models.CharField(max_length=50)


class staff(models.Model):
    DEPARTMENT = models.ForeignKey(department, on_delete=models.CASCADE, default=1)
    BATCH = models.ForeignKey(batch, on_delete=models.CASCADE, default=1)
    FACULTY = models.ForeignKey(faculty, on_delete=models.CASCADE, default=1)
    post=models.CharField(max_length=50)


class chat(models.Model):
    STAFF = models.ForeignKey(faculty, on_delete=models.CASCADE, default=1)
    STUDENT = models.ForeignKey(student, on_delete=models.CASCADE, default=1)
    date=models.CharField(max_length=50)
    message=models.CharField(max_length=500)
    type=models.CharField(max_length=50,default="")

class notification(models.Model):
    LOGIN = models.ForeignKey(login, on_delete=models.CASCADE, default=1)
    notification=models.CharField(max_length=50)
    date = models.DateField()



class timetable(models.Model):
    SUBJECT = models.ForeignKey(subject, on_delete=models.CASCADE, default=1)
    day = models.CharField(max_length=50)
    hour = models.CharField(max_length=50)
    PROGRAM=models.ForeignKey(program,on_delete=models.CASCADE,default=1)

class suspend(models.Model):
    PROGRAM = models.ForeignKey(program, on_delete=models.CASCADE, default=1)
    FACULTY = models.ForeignKey(faculty, on_delete=models.CASCADE, default=1)
    TIMETABLE = models.ForeignKey(timetable, on_delete=models.CASCADE, default=1)
    date = models.CharField(max_length=50)
    hour = models.CharField(max_length=50)
    type = models.CharField(max_length=50)

class specialclass(models.Model):
    SUBJECT = models.ForeignKey(subject, on_delete=models.CASCADE, default=1)
    date = models.CharField(max_length=50)
    time = models.CharField(max_length=50)


class attendance(models.Model):
    STUDENT = models.ForeignKey(student, on_delete=models.CASCADE, default=1)
    date= models.CharField(max_length=50)
    hour= models.CharField(max_length=50)
    percentage=models.CharField(max_length=50)
    TIMETABLE=models.ForeignKey(timetable,on_delete=models.CASCADE,default=1)

class exam(models.Model):
    SUBJECT = models.ForeignKey(subject, on_delete=models.CASCADE, default=1)
    STUDENT = models.ForeignKey(student, on_delete=models.CASCADE, default=1)
    date = models.CharField(max_length=50)
    mark = models.CharField(max_length=50)

class internal(models.Model):
    # ATTENDANCE = models.ForeignKey(attendance, on_delete=models.CASCADE, default=1)
    # ASSIGNMENT = models.ForeignKey(assignment, on_delete=models.CASCADE, default=1)
    SUBJECT = models.ForeignKey(subject, on_delete=models.CASCADE, default=1)
    STUDENT = models.ForeignKey(student, on_delete=models.CASCADE, default=1)
    # EXAM = models.ForeignKey(exam, on_delete=models.CASCADE, default=1)
    mark = models.CharField(max_length=50)
    date = models.CharField(max_length=50,default=0)


class calendar(models.Model):
    title=models.CharField(max_length=100,default="")
    url=models.CharField(max_length=100,default="")
    clas=models.CharField(max_length=100,default="")
    startdate=models.CharField(max_length=100,default="")
    enddate=models.CharField(max_length=100,default="")



class substitution(models.Model):
    SUBJECT = models.ForeignKey(subject, on_delete=models.CASCADE, default=1)
    FACULTY = models.ForeignKey(faculty, on_delete=models.CASCADE, default=1)
    date = models.CharField(max_length=50)
    hour = models.CharField(max_length=50)


class medical(models.Model):
    FACULTY = models.ForeignKey(faculty, on_delete=models.CASCADE, default=1)
    STUDENT = models.ForeignKey(student, on_delete=models.CASCADE, default=1)
    date = models.DateField(max_length=50)
    from_date = models.CharField(max_length=50)
    to_date = models.CharField(max_length=50)
    reason = models.CharField(max_length=50)
    status = models.CharField(max_length=50,default="pending")
    certificate = models.CharField(max_length=300)




class events(models.Model):
    start=models.CharField(max_length=100,default="")
    end=models.CharField(max_length=100,default="")
    text=models.CharField(max_length=100,default="")
    color=models.CharField(max_length=100,default="")
    bg=models.CharField(max_length=100,default="")


class assignmentfiles(models.Model):
    ASSIGNMENT = models.ForeignKey(assignment, on_delete=models.CASCADE, default=1)
    file=models.CharField(max_length=500)
    STUDENT=models.ForeignKey(student, on_delete=models.CASCADE, default=1)
    date=models.CharField(max_length=50)