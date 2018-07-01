<template>
  <q-layout view="lHh Lpr lFf">
    <q-layout-header>
      <q-toolbar
        color="primary"
        :glossy="$q.theme === 'mat'"
        :inverted="$q.theme === 'ios'"
      >
        <q-btn
          flat
          dense
          round
          @click="leftDrawerOpen = !leftDrawerOpen"
          aria-label="Menu"
        >
          <q-icon name="menu" />
        </q-btn>

        <q-toolbar-title>
          Trabalho - Desenvolvimento de Sistemas
          <div slot="subtitle">Versão - {{ $q.version }}</div>
        </q-toolbar-title>
      </q-toolbar>
    </q-layout-header>

    <q-layout-drawer
      v-model="leftDrawerOpen"
      :content-class="$q.theme === 'mat' ? 'bg-grey-2' : null"
    >
      <q-list
        no-border
        link
        inset-delimiter
      >
        <q-list-header>Menu</q-list-header>
        <q-item @click.native="openURL('http://localhost:8080/cargo')">
          <q-item-side icon="work" />
          <q-item-main label="Cargo"/>
        </q-item>
        <q-item @click.native="openURL('http://localhost:8080/equipe')">
          <q-item-side icon="people" />
          <q-item-main label="Equipe"/>
        </q-item>
        <q-item @click.native="openURL('http://localhost:8080/funcionario')">
          <q-item-side icon="person" />
          <q-item-main label="Funcionário" />
        </q-item>
        <q-item @click.native="openURL('http://localhost:8080/empresa')">
          <q-item-side icon="account_box" />
          <q-item-main label="Empresa" />
        </q-item>
      </q-list>
    </q-layout-drawer>

    <q-page-container>
      <router-view />
    </q-page-container>
  </q-layout>
</template>

<script>
import { openURL } from 'quasar'

export default {
  name: 'LayoutDefault',
  data () {
    return {
      leftDrawerOpen: this.$q.platform.is.desktop
    }
  },
  methods: {
    openURL
  }
}
</script>

<style>
</style>
