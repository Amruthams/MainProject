# Generated by Django 3.2.18 on 2023-03-12 04:34

from django.db import migrations, models


class Migration(migrations.Migration):

    dependencies = [
        ('EdulinkApp', '0004_calendar'),
    ]

    operations = [
        migrations.AddField(
            model_name='program',
            name='sem',
            field=models.CharField(default='', max_length=50),
        ),
    ]