# Generated by Django 3.2.18 on 2023-04-17 04:13

from django.db import migrations, models


class Migration(migrations.Migration):

    dependencies = [
        ('EdulinkApp', '0018_auto_20230417_0942'),
    ]

    operations = [
        migrations.AddField(
            model_name='internal',
            name='date',
            field=models.CharField(default=0, max_length=50),
        ),
    ]
