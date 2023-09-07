from itertools import product
def solution(user_id, banned_id):
    banned_list = []
    for bid in banned_id:
        tmp = []
        for uid in user_id:
            if len(bid) != len(uid):
                continue
            check = True
            for i in range(len(bid)):
                if bid[i] == '*':
                    continue
                elif bid[i] != uid[i]:
                    check = False
            if check:
                tmp.append(uid)
        banned_list.append(tmp)
    banned_product = list(product(*banned_list))
    result = []
    for products in banned_product:
        tmp_pro = set(products)
        if len(tmp_pro) == len(banned_id):
            if tmp_pro not in result:
                result.append(tmp_pro)
    return len(result)