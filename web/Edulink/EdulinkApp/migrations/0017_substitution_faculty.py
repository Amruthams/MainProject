# Generated by Django 3.2.18 on 2023-04-10 08:05

from django.db import migrations, models
import django.db.models.deletion


class Migration(migrations.Migration):

    dependencies = [
        ('EdulinkApp', '0016_auto_20230410_1320'),
    ]

    operations = [
        migrations.AddField(
            model_name='substitution',
            name='FACULTY',
            field=models.ForeignKey(default=1, on_delete=django.db.models.deletion.CASCADE, to='EdulinkApp.faculty'),
        ),
    ]
