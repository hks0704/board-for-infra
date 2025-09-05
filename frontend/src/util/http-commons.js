import axios from 'axios'

const { VITE_BACKEND_BASE_URL } = import.meta.env

// local vue api instance
function localAxios() {
  const instance = axios.create({
    baseURL: VITE_BACKEND_BASE_URL,
  })
  instance.defaults.headers.common['Authorization'] = ''
  instance.defaults.headers.post['Content-Type'] = 'application/json'
  instance.defaults.headers.put['Content-Type'] = 'application/json'

  return instance
}

export { localAxios }
