# Generated by Django 3.2.18 on 2023-04-20 10:31

from django.db import migrations, models


class Migration(migrations.Migration):

    dependencies = [
        ('EdulinkApp', '0022_medical_student'),
    ]

    operations = [
        migrations.AlterField(
            model_name='medical',
            name='date',
            field=models.DateField(max_length=50),
        ),
    ]