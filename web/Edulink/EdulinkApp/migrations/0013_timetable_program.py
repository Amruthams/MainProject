# Generated by Django 3.2.18 on 2023-03-28 09:13

from django.db import migrations, models
import django.db.models.deletion


class Migration(migrations.Migration):

    dependencies = [
        ('EdulinkApp', '0012_auto_20230315_1517'),
    ]

    operations = [
        migrations.AddField(
            model_name='timetable',
            name='PROGRAM',
            field=models.ForeignKey(default=1, on_delete=django.db.models.deletion.CASCADE, to='EdulinkApp.program'),
        ),
    ]
