
export default [
  {
    path: '/',
    component: () => import('layouts/default'),
    children: [
      { path: '', component: () => import('pages/index') },
      { path: '/cargo', component: () => import('components/cargo/cargo') },
      { path: '/equipe', component: () => import('components/equipe/equipe') },
      { path: '/funcionario', component: () => import('components/funcionario/funcionario') },
      { path: '/empresa', component: () => import('components/empresa/empresa') }
    ]
  },

  { // Always leave this as last one
    path: '*',
    component: () => import('pages/404')
  }
]
