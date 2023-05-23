function getCompanyPage(params){
    return $axios({
        url: '/company/page',
        method: 'get',
        params
    })
}

function deleteCompany(id) {
    return $axios({
        url: '/company',
        method: 'delete',
        params: { id }
    })
}

function editCompany(params) {
    return $axios({
        url: '/company/',
        method: 'put',
        data: { ...params }
    })
}

function findById(id) {
    return $axios({
        url: `/category`,
        method: 'get',
        params: { id }
    })
}

function addCompany(params) {
    return $axios({
        url: '/company',
        method: 'post',
        data: { ...params }
    })
}

function selectCompanyOptions() {
    return $axios({
        url: '/company/selectOptions',
        method: 'get',
        data: {  }
    })
}