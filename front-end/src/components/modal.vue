<template>
  <q-modal
    v-model="opened"
    class="coolar-modal"
    :noEscDismiss="true"
    :noBackdropDismiss="true"
    :content-css="{minWidth, minHeight}">
    <q-modal-layout>
      <q-toolbar slot="header">
        <q-btn
          flat
          round
          dense
          @click="close"
          icon="reply"
        />
        <q-toolbar-title>
          {{title}}
        </q-toolbar-title>
      </q-toolbar>

      <div class="coolar-layout-padding">
        <slot></slot>
      </div>

      <q-toolbar
        slot="footer"
        color="white"
        class="coolar-toolbar-footer">
        <q-toolbar-title class="flex justify-end">
         <q-btn color="warning" @click="close" label="Cancelar" />
         <q-btn color="positive" @click="save" label="Salvar" />
        </q-toolbar-title>
      </q-toolbar>
    </q-modal-layout>
  </q-modal>
</template>

<script>
import {
  QModal,
  QModalLayout,
  QSearch
} from 'quasar'

export default {
  props: {
    save: {
      type: Function,
      default () { return () => {} }
    },
    title: {
      type: String,
      required: true
    },
    minHeight: {
      type: String,
      default: '40vh'
    },
    minWidth: {
      type: String,
      default: '70vw'
    }
  },
  data () {
    return {
      opened: false
    }
  },
  components: {
    QModal,
    QModalLayout,
    QSearch
  },
  methods: {
    open () {
      this.opened = true
    },
    close () {
      this.opened = false
    }
  }
}
</script>
<style>
  .coolar-layout-padding {
    padding: 10px;
    height: calc(100% - 8px);
  }
  .coolar-modal .q-layout-footer {
    box-shadow: none
  }
  .coolar-toolbar-footer {
    padding: 4px 0px;
  }
  .coolar-toolbar-footer .q-btn {
    margin-left: 8px;
  }
</style>
