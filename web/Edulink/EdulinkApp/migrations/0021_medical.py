# Generated by Django 3.2.18 on 2023-04-20 05:06

from django.db import migrations, models
import django.db.models.deletion


class Migration(migrations.Migration):

    dependencies = [
        ('EdulinkApp', '0020_events'),
    ]

    operations = [
        migrations.CreateModel(
            name='medical',
            fields=[
                ('id', models.BigAutoField(auto_created=True, primary_key=True, serialize=False, verbose_name='ID')),
                ('date', models.CharField(max_length=50)),
                ('from_date', models.CharField(max_length=50)),
                ('to_date', models.CharField(max_length=50)),
                ('reason', models.CharField(max_length=50)),
                ('status', models.CharField(max_length=50)),
                ('certificate', models.CharField(max_length=300)),
                ('FACULTY', models.ForeignKey(default=1, on_delete=django.db.models.deletion.CASCADE, to='EdulinkApp.faculty')),
            ],
        ),
    ]
