# Generated by Django 3.2.18 on 2023-04-20 07:13

from django.db import migrations, models
import django.db.models.deletion


class Migration(migrations.Migration):

    dependencies = [
        ('EdulinkApp', '0021_medical'),
    ]

    operations = [
        migrations.AddField(
            model_name='medical',
            name='STUDENT',
            field=models.ForeignKey(default=1, on_delete=django.db.models.deletion.CASCADE, to='EdulinkApp.student'),
        ),
    ]