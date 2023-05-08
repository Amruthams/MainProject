# (A) LOAD SQLITE MODULE
from calendar import monthrange

from EdulinkApp.models import events


def get1(request,month, year):


  daysInMonth = str(monthrange(year, month)[1])
  month = month if month>10 else "0" + str(month)
  dateYM = str(year) + "-" + str(month) + "-"
  start = dateYM + "01 00:00:00"
  end = dateYM + daysInMonth + " 23:59:59"

  # (D3) GET EVENTS
  res=events.objects.raw("SELECT * FROM `events` WHERE ((`start` BETWEEN '"+start+"' AND '"+end+"') OR (`end` BETWEEN '"+start+"' AND '"+end+"') OR (`start` <= '"+start+"' AND `end` >= '"+end+"'))")


  if len(res)==0:
    return None

  # s & e : start & end date
  # c & b : text & background color
  # t : event text
  data ={}
  for r in res:

    data[r["id"]] = {
      "s" : r["start"], "e" : r["end"],
      "c" : r["color"], "b" : r["bg"],
      "t" : r["text"]
    }
  return data



def save (request,start, end, txt, color, bg, id=None):

  if id is None:
      eobj=events()
      eobj.start=start
      eobj.end=end
      eobj.text=txt
      eobj.color=color
      eobj.bg=bg
      eobj.save()
  else:
      eobj=events.objects.get(id=id)
      eobj.start = start
      eobj.end = end
      eobj.text = txt
      eobj.color = color
      eobj.bg = bg
      eobj.save()

  return True




def delete(id):
    eobj = events.objects.get(id=id)
    eobj.delete()
    return True



