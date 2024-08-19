import { defineConfig } from 'vite'
import preact from '@preact/preset-vite'

// vite.config.js
export default {
  server: {
    proxy: {
      '/api': 'http://localhost:8080', // Adjust the port as needed
    },
  },
};
