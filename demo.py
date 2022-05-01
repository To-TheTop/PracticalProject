import requests
import json

# 伪装自己,冒充成微信小程序
headers = {
    'User-Agent': 'Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/53.0.2785.143 Safari/537.36 MicroMessenger/7.0.9.501 NetType/WIFI MiniProgramEnv/Windows WindowsWechat'
}
# 需要访问的地址
url = 'https://j1.pupuapi.com/client/marketing/storeproduct/v2/search?store_id=deef1dd8-65ee-46bc-9e18-8cf1478a67e9&page=1&size=20&name=&category_id=&sort=0&tags=&brands=&in_stock=-1&is_commend=-1&business=scenes&business_id=130'

# 从url中得到json数据
products = requests.get(url=url, headers=headers).json()
products = products['data']['products']
data = json.dumps(products, ensure_ascii=False, indent=1)
