const getBaseUrl = (url) => {
  let BASE_URL = '';
  if (process.env.NODE_ENV === 'development') {
    //开发环境 - 根据请求不同返回不同的BASE_URL
    if (url.includes('/api/')) {
      BASE_URL = ''
    } else if (url.includes('/iatadatabase/')) {
      BASE_URL = ''
    } else {
      BASE_URL = 'http://172.26.120.50:8080'
    }
  } else {
    // 生产环境
    if (url.includes('/api/')) {
      BASE_URL = ''
    } else if (url.includes('/iatadatabase/')) {
      BASE_URL = ''
    } else {
      BASE_URL = 'http://172.26.120.50:8080'
    }
  }
  return BASE_URL
}

export default getBaseUrl;
