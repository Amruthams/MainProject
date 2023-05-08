
import datetime
import numpy as np

start = datetime.date(2023, 5, 1)
end = datetime.date(2023, 5, 31)

days = np.busday_count(start, end)
print('Number of business days is:', days)