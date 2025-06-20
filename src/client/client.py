import requests
import random
import threading
import time

BASE_URL = 'URL'


def client_workflow(client_id):
    for _ in range(10):
        book_id = random.randint(1, 10)
        r = requests.get(f"{BASE_URL}/{book_id}")
        print(f"Client {client_id}: Lookup book {book_id} - Status {r.status_code}")

        if random.randint(0, 100) > 50:
            action = random.choice(['borrow', 'extend', 'buy', 'sell'])
            if action == 'extend':
                res = requests.put(f"{BASE_URL}/{book_id}/extend")
            else:
                res = requests.post(f"{BASE_URL}/{book_id}/{action}")
            print(f"Client {client_id}: Action {action} on book {book_id} - Status {res.status_code}")
        time.sleep(0.2)


threads = []
for i in range(5):
    t = threading.Thread(target=client_workflow, args=(i + 1,))
    t.start()
    threads.append(t)

for t in threads:
    t.join()
